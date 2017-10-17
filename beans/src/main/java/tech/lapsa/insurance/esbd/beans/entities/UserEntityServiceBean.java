package com.lapsa.insurance.esbd.services.impl.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfUser;
import com.lapsa.esbd.jaxws.client.User;
import com.lapsa.insurance.esbd.domain.entities.general.UserEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.general.BranchServiceDAO;
import com.lapsa.insurance.esbd.services.general.InsuranceCompanyServiceDAO;
import com.lapsa.insurance.esbd.services.general.SubjectServiceDAO;
import com.lapsa.insurance.esbd.services.general.UserServiceDAO;

import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;

@Stateless
public class UserEntityServiceWS extends AbstractESBDEntityServiceWS implements UserServiceDAO {

    @EJB
    private BranchServiceDAO branchService;

    @EJB
    private SubjectServiceDAO subjectService;

    @EJB
    private InsuranceCompanyServiceDAO insuranceCompanyService;

    private List<UserEntity> all;

    @Inject
    private ESBDConnectionPool pool;

    private void lazyInit() {
	if (all != null)
	    return;
	try (ESBDConnection con = pool.getConnection()) {
	    ArrayOfUser users = con.getUsers();
	    if (users == null)
		return;
	    all = users.getUser().stream() //
		    .map(this::convert) //
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    @Override
    public List<UserEntity> getAll() {
	lazyInit();
	return new ArrayList<>(all);
    }

    @Override
    public UserEntity getById(final Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	lazyInit();
	return all.stream() //
		.filter(x -> x.getId() == id) //
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
	try {
	    if (MyNumbers.nonZero(source.getBranchID()))
		target.setBranch(branchService.getById(new Long(source.getBranchID())));
	} catch (NotFound e) {
	    // non mandatory field
	}

	// CLIENT_ID s:int Клиент пользователя (справочник CLIENTS)
	target.setSubjectId(new Long(source.getCLIENTID()));

	// SYSTEM_DELIMITER_ID s:int Разделитель учета (справочник
	// SYSTEM_DELIMITER)
	try {
	    if (MyNumbers.nonZero(source.getSYSTEMDELIMITERID()))
		target.setOrganization(insuranceCompanyService.getById(new Long(source.getSYSTEMDELIMITERID())));
	} catch (NotFound e) {
	    // non mandatory field
	}

	// IsAuthenticated s:int Пользователь аутентифицирован
	target.setAuthentificated(source.getIsAuthenticated() == 1);

	// SessionID s:string Идентификатор текущей сессии пользователя
	target.setLastSesionId(source.getSessionID());

	// ErrorMessage s:string Описание ошибки аутентификации
	// LastRequestTime s:string Время последнего действия пользователя
    }

}
