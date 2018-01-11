package tech.lapsa.esbd.dao.entities;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.BranchEntityService.BranchEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectEntityService.SubjectEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.UserEntityService.UserEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.UserEntityService.UserEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfUser;
import tech.lapsa.esbd.jaxws.wsimport.User;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.logging.MyLogger;

@Singleton(name = UserEntityService.BEAN_NAME)
@Startup
public class UserEntityServiceBean
	implements UserEntityServiceLocal, UserEntityServiceRemote {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(UserEntityService.class) //
	    .build();

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserEntity> getAll() {
	try {
	    return _getAll();
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    public UserEntity getById(final Integer id) throws NotFound, IllegalArgument {
	try {
	    return _getById(id);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    @EJB
    private BranchEntityServiceLocal branchService;

    @EJB
    private InsuranceCompanyEntityServiceLocal insuranceCompanyService;

    @EJB
    private ConnectionPool pool;

    private static class Holder {
	private final User esbd;
	private UserEntity fetched;

	private Holder(User esbd) {
	    this.esbd = esbd;
	}
    }

    private Map<Integer, Holder> all;

    @PostConstruct
    @Schedule(dayOfWeek = "*")
    public void reload() {
	final ArrayOfUser items;
	try (Connection con = pool.getConnection()) {
	    items = con.getUsers();
	}
	all = MyOptionals.of(items) //
		.map(ArrayOfUser::getUser) //
		.map(List::stream) //
		.orElseGet(Stream::empty) //
		.collect(MyCollectors.unmodifiableMap(User::getID, Holder::new));
    }

    private UserEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	final Holder holder = all.get(id);
	if (holder == null)
	    throw MyExceptions.format(NotFound::new, "%1$s(%2$s) not found", UserEntity.class.getSimpleName(), id);
	synchronized (holder) {
	    if (holder.fetched == null)
		holder.fetched = convert(holder.esbd);
	}
	return holder.fetched;
    }

    private List<UserEntity> _getAll() {
	try {
	    return all.entrySet()
		    .stream()
		    .map(Map.Entry::getValue)
		    .filter(MyObjects::nonNull)
		    .map(holder -> {
			synchronized (holder) {
			    if (holder.fetched == null)
				holder.fetched = convert(holder.esbd);
			}
			return holder.fetched;
		    })
		    .collect(MyCollectors.unmodifiableList());
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    UserEntity convert(final User source) {
	final UserEntity traget = new UserEntity();
	fillValues(source, traget);
	return traget;
    }

    @EJB
    private SubjectEntityServiceLocal subjects;

    void fillValues(final User source, final UserEntity target) {
	try {
	    // ID s:int Идентификатор пользователя
	    target.id = MyOptionals.of(source.getID()).orElse(null);

	    // Name s:string Имя пользователя
	    target.login = source.getName();

	    // Branch_ID s:int Филиал пользователя (справочник BRANCHES)
	    // non mandatory field
	    target._branch = source.getBranchID();
	    Util.optionalField(target, target.id, branchService::getById,
		    target::setBranch, "branch", BranchEntity.class,
		    MyOptionals.of(target._branch));

	    // CLIENT_ID s:int Клиент пользователя (справочник CLIENTS)
	    // non mandatory field
	    target._subject = source.getCLIENTID();
	    Util.optionalField(target, target.id, subjects::getById,
		    target::setSubject, "subject", SubjectEntity.class,
		    MyOptionals.of(target._subject));

	    // SYSTEM_DELIMITER_ID s:int Разделитель учета (справочник
	    // SYSTEM_DELIMITER)
	    // non mandatory field
	    target._organization = source.getSYSTEMDELIMITERID();
	    Util.optionalField(target, target.id, insuranceCompanyService::getById,
		    target::setOrganization, "organization", InsuranceCompanyEntity.class,
		    MyOptionals.of(target._organization));

	    // IsAuthenticated s:int Пользователь аутентифицирован
	    target.authentificated = source.getIsAuthenticated() == 1;

	    // SessionID s:string Идентификатор текущей сессии пользователя
	    target.lastSesionId = source.getSessionID();

	    // ErrorMessage s:string Описание ошибки аутентификации
	    // LastRequestTime s:string Время последнего действия пользователя
	} catch (IllegalArgumentException e) {
	    // it should not happens
	    throw new EJBException(e.getMessage());
	}
    }

}
