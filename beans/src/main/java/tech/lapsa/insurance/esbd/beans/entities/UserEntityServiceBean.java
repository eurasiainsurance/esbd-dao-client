package tech.lapsa.insurance.esbd.beans.entities;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfUser;
import tech.lapsa.esbd.jaxws.wsimport.User;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.dict.BranchEntityService;
import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntityService;
import tech.lapsa.insurance.esbd.entities.UserEntity;
import tech.lapsa.insurance.esbd.entities.UserEntityService;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;

@Stateless
public class UserEntityServiceBean implements UserEntityService {

    @Inject
    private BranchEntityService branchService;

    @Inject
    private InsuranceCompanyEntityService insuranceCompanyService;

    @Inject
    private ConnectionPool pool;

    private List<UserEntity> all;

    @Override
    public List<UserEntity> getAll() {
	if (all != null)
	    return all;
	try (Connection con = pool.getConnection()) {
	    ArrayOfUser users = con.getUsers();
	    if (users == null)
		return (all = Collections.unmodifiableList(Collections.emptyList()));
	    return (all = users.getUser().stream() //
		    .map(this::convert) //
		    .collect(MyCollectors.unmodifiableList()));
	}
    }

    @Override
    public UserEntity getById(final Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	return getAll().stream() //
		.filter(x -> MyNumbers.numbericEquals(id, x.getId())) //
		.findAny() //
		.orElseThrow(
			() -> new NotFound(UserEntity.class.getSimpleName() + " not found with ID = '" + id + "'"));
    }

    UserEntity convert(User source) {
	UserEntity traget = new UserEntity();
	fillValues(source, traget);
	return traget;
    }

    void fillValues(User source, UserEntity target) {
	// ID s:int Идентификатор пользователя
	target.setId(source.getID());

	// Name s:string Имя пользователя
	target.setLogin(source.getName());

	// Branch_ID s:int Филиал пользователя (справочник BRANCHES)

	// non mandatory field
	target.setBranch(MyOptionals.of(source.getBranchID()) //
		.flatMap(branchService::optionalById) //
		.orElse(null));

	// CLIENT_ID s:int Клиент пользователя (справочник CLIENTS)
	target.setSubjectId(new Long(source.getCLIENTID()));

	// SYSTEM_DELIMITER_ID s:int Разделитель учета (справочник
	// SYSTEM_DELIMITER)

	// non mandatory field
	target.setOrganization(MyOptionals.of(source.getSYSTEMDELIMITERID()) //
		.flatMap(insuranceCompanyService::optionalById) //
		.orElse(null));

	// IsAuthenticated s:int Пользователь аутентифицирован
	target.setAuthentificated(source.getIsAuthenticated() == 1);

	// SessionID s:string Идентификатор текущей сессии пользователя
	target.setLastSesionId(source.getSessionID());

	// ErrorMessage s:string Описание ошибки аутентификации
	// LastRequestTime s:string Время последнего действия пользователя
    }

}
