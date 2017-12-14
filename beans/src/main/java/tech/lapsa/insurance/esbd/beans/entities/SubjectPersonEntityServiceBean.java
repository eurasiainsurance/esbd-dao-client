package tech.lapsa.insurance.esbd.beans.entities;

import static tech.lapsa.insurance.esbd.beans.ESBDDates.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.elements.GenderService.GenderServiceLocal;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService.IdentityCardTypeServiceLocal;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntity;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntityService.SubjectPersonEntityServiceLocal;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntityService.SubjectPersonEntityServiceRemote;
import tech.lapsa.insurance.esbd.infos.IdentityCardInfo;
import tech.lapsa.insurance.esbd.infos.PersonalInfo;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Stateless
public class SubjectPersonEntityServiceBean extends ASubjectEntityService
	implements SubjectPersonEntityServiceLocal, SubjectPersonEntityServiceRemote {

    @EJB
    private IdentityCardTypeServiceLocal identityCardTypeService;

    @EJB
    private GenderServiceLocal sexService;

    @Override
    public SubjectPersonEntity getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");

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

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SubjectPersonEntity getByIIN(final TaxpayerNumber taxpayerNumber) throws NotFound, IllegalArgument {
	MyObjects.requireNonNull(IllegalArgument::new, taxpayerNumber, "taxpayerNumber"); //
	TaxpayerNumber.requireValid(IllegalArgument::new, taxpayerNumber);

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
	di.setDateOfIssue(convertESBDDateToCalendar(source.getDOCUMENTGIVEDDATE()));
	di.setIssuingAuthority(source.getDOCUMENTGIVEDBY());
	di.setNumber(source.getDOCUMENTNUMBER());

	// non mandatory field
	di.setIdentityCardType(MyOptionals.of(source.getDOCUMENTTYPEID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> identityCardTypeService.getById(id))) //
		.orElse(null));
    }
}
