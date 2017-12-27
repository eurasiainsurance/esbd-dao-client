package tech.lapsa.esbd.dao.beans.entities;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectCompanyEntity;
import tech.lapsa.esbd.dao.entities.SubjectCompanyEntityService;
import tech.lapsa.esbd.dao.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceRemote;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntity;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Stateless(name = SubjectCompanyEntityService.BEAN_NAME)
public class SubjectCompanyEntityServiceBean extends ASubjectEntityService
	implements SubjectCompanyEntityServiceLocal, SubjectCompanyEntityServiceRemote {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(SubjectCompanyEntityService.class) //
	    .build();

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SubjectCompanyEntity getById(final Integer id) throws NotFound, IllegalArgument {
	try {
	    return _getById(id);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SubjectCompanyEntity getByBIN(final TaxpayerNumber taxpayerNumber) throws NotFound, IllegalArgument {
	try {
	    return _getByBIN(taxpayerNumber);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    @EJB
    private CompanyActivityKindEntityServiceLocal companyActivityKindService;

    private SubjectCompanyEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (Connection con = pool.getConnection()) {
	    final Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    final boolean isNotPerson = source.getNaturalPersonBool() == 0;
	    if (!isNotPerson)
		throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with ID = '" + id
			+ "'. It was a " + SubjectPersonEntity.class.getName());
	    return convert(source);
	}
    }

    private SubjectCompanyEntity _getByBIN(final TaxpayerNumber taxpayerNumber)
	    throws IllegalArgumentException, NotFound {
	MyObjects.requireNonNull(taxpayerNumber, "taxpayerNumber"); //
	TaxpayerNumber.requireValid(taxpayerNumber);

	final Client source = fetchClientByIdNumber(taxpayerNumber, false, true);
	if (source == null)
	    throw new NotFound(
		    SubjectCompanyEntity.class.getSimpleName() + " not found with 'bin' = '"
			    + taxpayerNumber.getNumber() + "'");
	final boolean isNotPerson = source.getNaturalPersonBool() == 0;
	if (!isNotPerson)
	    throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with 'bin' = '"
		    + taxpayerNumber.getNumber()
		    + "'. It was a " + SubjectPersonEntity.class.getName());
	return convert(source);
    }

    protected SubjectCompanyEntity convert(final Client source) {
	final SubjectCompanyEntity target = new SubjectCompanyEntity();
	fillValues(source, target);
	return target;
    }

    protected void fillValues(final Client source, final SubjectCompanyEntity target) {
	super.fillValues(source, target);

	// Juridical_Person_Name s:string Наименование (для юр. лица)
	target.setCompanyName(source.getJuridicalPersonName());

	// MAIN_CHIEF s:string Первый руководитель
	target.setHeadName(source.getMAINCHIEF());

	// MAIN_ACCOUNTANT s:string Главный бухгалтер
	target.setAccountantName(source.getMAINACCOUNTANT());

	// ACTIVITY_KIND_ID s:int Вид деятельности (справочник ACTIVITY_KINDS)
	target.setCompanyActivityKindId(new Long(source.getACTIVITYKINDID()));

	// non mandatory field
	target.setCompanyActivityKind(MyOptionals.of(source.getACTIVITYKINDID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> companyActivityKindService.getById(id))) //
		.orElse(null));
    }
}
