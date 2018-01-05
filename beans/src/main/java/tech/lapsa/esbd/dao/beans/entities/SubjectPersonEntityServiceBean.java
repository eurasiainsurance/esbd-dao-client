package tech.lapsa.esbd.dao.beans.entities;

import static tech.lapsa.esbd.dao.beans.ESBDDates.*;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.GenderService.GenderServiceLocal;
import tech.lapsa.esbd.dao.elements.IdentityCardTypeService.IdentityCardTypeServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectCompanyEntity;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntity;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntityService;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntityService.SubjectPersonEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntityService.SubjectPersonEntityServiceRemote;
import tech.lapsa.esbd.dao.infos.IdentityCardInfo;
import tech.lapsa.esbd.dao.infos.PersonalInfo;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Stateless(name = SubjectPersonEntityService.BEAN_NAME)
public class SubjectPersonEntityServiceBean extends ASubjectEntityService
	implements SubjectPersonEntityServiceLocal, SubjectPersonEntityServiceRemote {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(SubjectPersonEntityService.class) //
	    .build();

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SubjectPersonEntity getById(final Integer id) throws NotFound, IllegalArgument {
	try {
	    return _getById(id);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SubjectPersonEntity getByIIN(final TaxpayerNumber taxpayerNumber) throws NotFound, IllegalArgument {
	try {
	    return _getByIIN(taxpayerNumber);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    @EJB
    private IdentityCardTypeServiceLocal identityCardTypeService;

    @EJB
    private GenderServiceLocal sexService;

    private SubjectPersonEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (Connection con = pool.getConnection()) {
	    final Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectPersonEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    final boolean isNotPerson = source.getNaturalPersonBool() == 0;
	    if (isNotPerson)
		throw new NotFound(SubjectPersonEntity.class.getSimpleName() + " not found with ID = '" + id
			+ "'. It was a " + SubjectCompanyEntity.class.getName());
	    return convert(source);
	}
    }

    private SubjectPersonEntity _getByIIN(final TaxpayerNumber taxpayerNumber)
	    throws IllegalArgumentException, NotFound {
	MyObjects.requireNonNull(taxpayerNumber, "taxpayerNumber"); //
	TaxpayerNumber.requireValid(taxpayerNumber);

	final Client source = fetchClientByIdNumber(taxpayerNumber, true, false);
	if (source == null)
	    throw new NotFound(
		    SubjectPersonEntity.class.getSimpleName() + " not found with 'iin' = '" + taxpayerNumber.getNumber()
			    + "'");
	final boolean isNotPerson = source.getNaturalPersonBool() == 0;
	if (isNotPerson)
	    throw new NotFound(
		    SubjectPersonEntity.class.getSimpleName() + " not found with 'iin' = '" + taxpayerNumber.getNumber()
			    + "'. It was a " + SubjectCompanyEntity.class.getName());
	final SubjectPersonEntity res = convert(source);
	return res;
    }

    protected SubjectPersonEntity convert(final Client source) {
	final SubjectPersonEntity target = new SubjectPersonEntity();
	fillValues(source, target);
	return target;
    }

    protected void fillValues(final Client source, final SubjectPersonEntity target) {
	super.fillValues(source, target);

	// First_Name s:string Имя (для физ. лица)
	// Last_Name s:string Фамилия (для физ. лица)
	// Middle_Name s:string Отчество (для физ. лица)
	// Born s:string Дата рождения
	// Sex_ID s:int Пол (справочник SEX)
	final PersonalInfo pi = new PersonalInfo();
	target.setPersonal(pi);
	pi.setName(source.getFirstName());
	pi.setSurename(source.getLastName());
	pi.setPatronymic(source.getMiddleName());
	pi.setDayOfBirth(convertESBDDateToLocalDate(source.getBorn()));
	// non mandatory
	pi.setSex(MyOptionals.of(source.getSexID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> sexService.getById(id))) //
		.orElse(null));

	// DOCUMENT_TYPE_ID s:int Тип документа (справочник DOCUMENTS_TYPES)
	// DOCUMENT_NUMBER s:string Номер документа
	// DOCUMENT_GIVED_BY s:string Документ выдан
	// DOCUMENT_GIVED_DATE s:string Дата выдачи документа
	final IdentityCardInfo di = new IdentityCardInfo();
	target.setIdentityCardInfo(di);
	di.setDateOfIssue(convertESBDDateToLocalDate(source.getDOCUMENTGIVEDDATE()));
	di.setIssuingAuthority(source.getDOCUMENTGIVEDBY());
	di.setNumber(source.getDOCUMENTNUMBER());

	// non mandatory field
	di.setIdentityCardType(MyOptionals.of(source.getDOCUMENTTYPEID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> identityCardTypeService.getById(id))) //
		.orElse(null));
    }
}
