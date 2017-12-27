package tech.lapsa.esbd.dao.beans.entities;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.dict.BranchEntityService.BranchEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.UserEntity;
import tech.lapsa.esbd.dao.entities.UserEntityService;
import tech.lapsa.esbd.dao.entities.UserEntityService.UserEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.UserEntityService.UserEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfUser;
import tech.lapsa.esbd.jaxws.wsimport.User;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;

@Stateless(name = UserEntityService.BEAN_NAME)
public class UserEntityServiceBean implements UserEntityServiceLocal, UserEntityServiceRemote {

    @EJB
    private BranchEntityServiceLocal branchService;

    @EJB
    private InsuranceCompanyEntityServiceLocal insuranceCompanyService;

    @EJB
    private ConnectionPool pool;

    private List<UserEntity> all;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserEntity> getAll() {
	if (all != null)
	    return all;
	try (Connection con = pool.getConnection()) {
	    final ArrayOfUser users = con.getUsers();
	    if (users == null)
		return all = Collections.unmodifiableList(Collections.emptyList());
	    return all = users.getUser().stream() //
		    .map(this::convert) //
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    @Override
    public UserEntity getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	return getAll().stream() //
		.filter(x -> MyNumbers.numbericEquals(id, x.getId())) //
		.findAny() //
		.orElseThrow(
			() -> new NotFound(UserEntity.class.getSimpleName() + " not found with ID = '" + id + "'"));
    }

    UserEntity convert(final User source) {
	final UserEntity traget = new UserEntity();
	fillValues(source, traget);
	return traget;
    }

    void fillValues(final User source, final UserEntity target) {
	// ID s:int Идентификатор пользователя
	target.setId(source.getID());

	// Name s:string Имя пользователя
	target.setLogin(source.getName());

	// Branch_ID s:int Филиал пользователя (справочник BRANCHES)

	// non mandatory field
	target.setBranch(MyOptionals.of(source.getBranchID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> branchService.getById(id))) //
		.orElse(null));

	// CLIENT_ID s:int Клиент пользователя (справочник CLIENTS)
	target.setSubjectId(new Long(source.getCLIENTID()));

	// SYSTEM_DELIMITER_ID s:int Разделитель учета (справочник
	// SYSTEM_DELIMITER)

	// non mandatory field
	target.setOrganization(MyOptionals.of(source.getSYSTEMDELIMITERID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> insuranceCompanyService.getById(id))) //
		.orElse(null));

	// IsAuthenticated s:int Пользователь аутентифицирован
	target.setAuthentificated(source.getIsAuthenticated() == 1);

	// SessionID s:string Идентификатор текущей сессии пользователя
	target.setLastSesionId(source.getSessionID());

	// ErrorMessage s:string Описание ошибки аутентификации
	// LastRequestTime s:string Время последнего действия пользователя
    }

}
