package tech.lapsa.esbd.dao.beans.entities;

import static tech.lapsa.esbd.dao.beans.ESBDDates.*;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.ws.soap.SOAPFaultException;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.dict.BranchEntityService.BranchEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.elements.CancelationReasonService.CancelationReasonServiceLocal;
import tech.lapsa.esbd.dao.elements.InsuranceClassTypeService.InsuranceClassTypeServiceLocal;
import tech.lapsa.esbd.dao.elements.InsuredAgeAndExpirienceClassService.InsuredAgeAndExpirienceClassServiceLocal;
import tech.lapsa.esbd.dao.elements.KZAreaService.KZAreaServiceLocal;
import tech.lapsa.esbd.dao.elements.MaritalStatusService.MaritalStatusServiceLocal;
import tech.lapsa.esbd.dao.elements.VehicleAgeClassService.VehicleAgeClassServiceLocal;
import tech.lapsa.esbd.dao.elements.VehicleClassService.VehicleClassServiceLocal;
import tech.lapsa.esbd.dao.entities.InsuredDriverEntity;
import tech.lapsa.esbd.dao.entities.InsuredVehicleEntity;
import tech.lapsa.esbd.dao.entities.PolicyEntity;
import tech.lapsa.esbd.dao.entities.PolicyEntityService;
import tech.lapsa.esbd.dao.entities.PolicyEntityService.PolicyEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.PolicyEntityService.PolicyEntityServiceRemote;
import tech.lapsa.esbd.dao.entities.SubjectEntityService.SubjectEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntityService.SubjectPersonEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.UserEntityService.UserEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleEntityService.VehicleEntityServiceLocal;
import tech.lapsa.esbd.dao.infos.DriverLicenseInfo;
import tech.lapsa.esbd.dao.infos.GPWParticipantInfo;
import tech.lapsa.esbd.dao.infos.InvalidInfo;
import tech.lapsa.esbd.dao.infos.PensionerInfo;
import tech.lapsa.esbd.dao.infos.PrivilegerInfo;
import tech.lapsa.esbd.dao.infos.RecordOperationInfo;
import tech.lapsa.esbd.dao.infos.VehicleCertificateInfo;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfDriver;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfPoliciesTF;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfPolicy;
import tech.lapsa.esbd.jaxws.wsimport.Driver;
import tech.lapsa.esbd.jaxws.wsimport.PoliciesTF;
import tech.lapsa.esbd.jaxws.wsimport.Policy;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless(name = PolicyEntityService.BEAN_NAME)
public class PolicyEntityServiceBean implements PolicyEntityServiceLocal, PolicyEntityServiceRemote {

    @EJB
    private InsuranceCompanyEntityServiceLocal insuranceCompanyService;

    @EJB
    private SubjectEntityServiceLocal subjectService;

    @EJB
    private CancelationReasonServiceLocal cancelationReasonTypeService;

    @EJB
    private BranchEntityServiceLocal branchService;

    @EJB
    private InsuredAgeAndExpirienceClassServiceLocal driverExpirienceClassificationService;

    @EJB
    private InsuranceClassTypeServiceLocal insuranceClassTypeService;

    @EJB
    private SubjectPersonEntityServiceLocal subjectPersonService;

    @EJB
    private MaritalStatusServiceLocal maritalStatusService;

    @EJB
    private UserEntityServiceLocal userService;

    @EJB
    private VehicleEntityServiceLocal vehicleService;

    @EJB
    private VehicleClassServiceLocal vehicleClassService;

    @EJB
    private VehicleAgeClassServiceLocal vehicleAgeClassService;

    @EJB
    private KZAreaServiceLocal countryRegionService;

    @EJB
    private ConnectionPool pool;

    @Override
    public PolicyEntity getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	try (Connection con = pool.getConnection()) {
	    Policy source = null;
	    try {
		source = con.getPolicyByID(id);
	    } catch (final SOAPFaultException e) {
		source = null;
	    }
	    if (source == null)
		throw new NotFound(PolicyEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    final PolicyEntity target = new PolicyEntity();
	    fillValues(source, target);
	    return target;
	}
    }

    @Override
    public PolicyEntity getByNumber(final String number) throws NotFound, IllegalArgument {
	MyStrings.requireNonEmpty(IllegalArgument::new, number, "number");

	try (Connection con = pool.getConnection()) {
	    final ArrayOfPolicy policies = con.getPoliciesByNumber(number);
	    if (policies == null || policies.getPolicy() == null || policies.getPolicy().isEmpty())
		throw new NotFound(PolicyEntity.class.getSimpleName() + " not found with NUMBER = '" + number + "'");
	    if (policies.getPolicy().size() > 1)
		throw new DataCoruptionException(
			"Too many " + PolicyEntity.class.getSimpleName() + " ("
				+ policies.getPolicy().size() + ") with NUMBER = '" + number + "'");

	    final PolicyEntity policy = new PolicyEntity();
	    fillValues(policies.getPolicy().iterator().next(), policy);
	    return policy;
	}
    }

    void fillValues(final Policy source, final PolicyEntity target) {
	// POLICY_ID s:int Идентификатор полиса (обязательно)
	target.setId(source.getPOLICYID());

	// POLICY_NUMBER s:string Номер полиса (обязательно)
	target.setNumber(source.getPOLICYNUMBER());

	// DATE_BEG s:string Дата начала действия полиса (обязательно)
	target.setValidFrom(convertESBDDateToCalendar(source.getDATEBEG()));

	// DATE_END s:string Дата окончания действия полиса (обязательно)
	target.setValidTill(convertESBDDateToCalendar(source.getDATEEND()));

	// PREMIUM s:double Страховая премия (обязательно)
	target.setActualPremiumCost(source.getPREMIUM());

	// CALCULATED_PREMIUM s:double Страховая премия рассчитанная системой
	target.setPremiumCost(source.getCALCULATEDPREMIUM());

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	try {
	    target.setInsurer(insuranceCompanyService.getById(source.getSYSTEMDELIMITERID()));
	} catch (NotFound | IllegalArgument e) {
	    // mandatory field
	    throw new DataCoruptionException(
		    "Error while fetching Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Insurance company ID = '" + source.getSYSTEMDELIMITERID()
			    + "' not found",
		    e);
	}

	// CLIENT_ID s:int Идентификатор страхователя (обязательно)
	try {
	    target.setInsurant(subjectService.getById(source.getCLIENTID()));
	} catch (NotFound | IllegalArgument e) {
	    // mandatory field
	    throw new DataCoruptionException(
		    "Error while fetching Policy ID = '" + source.getPOLICYID() + "' from ESBD. Insurer ID = '"
			    + source.getCLIENTID() + "' not found",
		    e);
	}

	// POLICY_DATE s:string Дата полиса
	target.setDateOfIssue(convertESBDDateToCalendar(source.getPOLICYDATE()));

	// RESCINDING_DATE s:string Дата расторжения полиса
	target.setDateOfCancelation(convertESBDDateToCalendar(source.getRESCINDINGDATE()));

	// RESCINDING_REASON_ID s:int Идентификатор причины расторжения

	// non mandatory field
	target.setCancelationReasonType(MyOptionals.of(source.getRESCINDINGREASONID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> cancelationReasonTypeService.getById(id))) //
		.orElse(null));

	// BRANCH_ID s:int Филиал (обязательно)
	try {
	    target.setBranch(branchService.getById(source.getBRANCHID()));
	} catch (NotFound | IllegalArgument e) {
	    // mandatory field
	    throw new DataCoruptionException(
		    "Error while fetching Policy from ESBD. Branch ID = '" + source.getBRANCHID() + "' not found", e);
	}

	// REWRITE_BOOL s:int Признак переоформления
	target.setReissued(source.getREWRITEBOOL() == 1);

	// REWRITE_POLICY_ID s:int Ссылка на переоформляемый полис
	target.setReissuedPolicyId(source.getREWRITEPOLICYID());

	// DESCRIPTION s:string Комментарии к полису
	target.setComments(source.getDESCRIPTION());

	// Drivers tns:ArrayOfDriver Водители (обязательно)
	final ArrayOfDriver drivers = source.getDrivers();
	if (drivers == null || drivers.getDriver() == null || drivers.getDriver().isEmpty())
	    throw new DataCoruptionException(
		    "Error while fetching Policy ID = '" + source.getPOLICYID() + "' from ESBD. Drivers list is empty");
	final List<InsuredDriverEntity> insuredDrivers = new ArrayList<>();
	target.setInsuredDrivers(insuredDrivers);
	for (final Driver s : drivers.getDriver()) {
	    final InsuredDriverEntity t = new InsuredDriverEntity();
	    fillValues(s, t, target);
	    insuredDrivers.add(t);
	}

	// PoliciesTF tns:ArrayOfPolicies_TF Транспортные средства полиса
	// (обязательно)
	final ArrayOfPoliciesTF vehicles = source.getPoliciesTF();
	if (vehicles == null || vehicles.getPoliciesTF() == null || vehicles.getPoliciesTF().isEmpty())
	    throw new DataCoruptionException(
		    "Error while fetching Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Insured vehicle list is empty");
	final List<InsuredVehicleEntity> insuredVehicles = new ArrayList<>();
	target.setInsuredVehicles(insuredVehicles);
	for (final PoliciesTF s : vehicles.getPoliciesTF()) {
	    final InsuredVehicleEntity t = new InsuredVehicleEntity();
	    fillValues(s, t, target);
	    insuredVehicles.add(t);
	}

	// CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего полис
	// INPUT_DATE s:string Дата\время ввода полиса в систему
	final RecordOperationInfo created = new RecordOperationInfo();
	target.setCreated(created);
	created.setDate(convertESBDDateToLocalDate(source.getINPUTDATE()));
	try {
	    created.setAuthor(userService.getById(source.getCREATEDBYUSERID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. User created ID = '" + source.getCREATEDBYUSERID()
			    + "' not found",
		    e);
	}

	// RECORD_CHANGED_AT s:string Дата\время изменения полиса
	// RECORD_CHANGED_AT_DATETIME s:string Дата\время изменения полиса
	// CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	// полис
	final RecordOperationInfo modified = new RecordOperationInfo();
	target.setModified(modified);
	modified.setDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()));
	if (modified.getDate() != null)
	    try {
		modified.setAuthor(userService.getById(source.getCHANGEDBYUSERID()));
	    } catch (NotFound | IllegalArgument e) {
		throw new DataCoruptionException(
			"Error while fetching Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. User modified ID = '" + source.getCHANGEDBYUSERID()
				+ "' not found",
			e);
	    }

	// GLOBAL_ID s:string Уникальный глобальный идентификатор полиса
	// ScheduledPayments tns:ArrayOfSCHEDULED_PAYMENT Плановые платежи по
	// полису
	// PAYMENT_ORDER_TYPE_ID s:int Порядок оплаты (Идентификатор)
	// PAYMENT_ORDER_TYPE s:string Порядок оплаты
	// PAYMENT_DATE s:string Дата оплаты
	// MIDDLEMAN_ID s:int Посредник (Идентификатор)
	// MIDDLEMAN_CONTRACT_NUMBER s:string Номер договора посредника
	// CLIENT_FORM_ID s:int Форма клиента (справочник CLIENT_FORMS)

    }

    void fillValues(final Driver source, final InsuredDriverEntity target, final PolicyEntity policy) {
	// DRIVER_ID s:int Идентификатор водителя
	target.setId(source.getDRIVERID());

	// POLICY_ID s:int Идентификатор полиса
	if (policy.getId() == source.getPOLICYID())
	    target.setPolicy(policy);
	else
	    try {
		target.setPolicy(getById(source.getPOLICYID()));
	    } catch (final NotFound | IllegalArgument e) {
		throw new DataCoruptionException(
			"Error while fetching Driver ID = '" + source.getDRIVERID()
				+ "' at Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. Policty with ID = '" + source.getPOLICYID() + "' not found");
	    }

	// CLIENT_ID s:int Идентификатор клиента (обязательно)
	try {
	    target.setInsuredPerson(subjectPersonService.getById(source.getCLIENTID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching Driver ID = '" + source.getDRIVERID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. CLIENT (individual) with ID = '" + source.getCLIENTID()
			    + "' not found",
		    e);
	}

	// HOUSEHOLD_POSITION_ID s:int Идентификатор семейного положения
	try {
	    target.setMaritalStatus(maritalStatusService.getById(source.getHOUSEHOLDPOSITIONID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching Driver ID = '" + source.getDRIVERID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Marital Status ID = '" + source.getHOUSEHOLDPOSITIONID()
			    + "' not found",
		    e);
	}

	// AGE_EXPERIENCE_ID s:int Идентификатор возраста\стажа вождения
	try {
	    target.setInsuredAgeExpirienceClass(
		    driverExpirienceClassificationService.getById(source.getAGEEXPERIENCEID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching Driver ID = '" + source.getDRIVERID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. AGEEXPIRIENCE with ID = '" + source.getAGEEXPERIENCEID() + "' not found");
	}

	// EXPERIENCE s:int Стаж вождения
	target.setDrivingExpirience(source.getEXPERIENCE());

	// DRIVER_CERTIFICATE s:string Номер водительского удостоверения
	// DRIVER_CERTIFICATE_DATE s:string Дата выдачи водительского
	// удостоверения
	final DriverLicenseInfo ci = new DriverLicenseInfo();
	target.setDriverLicense(ci);
	ci.setNumber(source.getDRIVERCERTIFICATE());
	ci.setDateOfIssue(convertESBDDateToLocalDate(source.getDRIVERCERTIFICATEDATE()));

	// getClassId
	try {
	    target.setInsuraceClassType(insuranceClassTypeService.getById(source.getClassId()));
	} catch (NotFound | IllegalArgument e) {
	    // mandatory field
	    throw new DataCoruptionException(
		    "Error while fetching Driver ID = '" + source.getDRIVERID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Class Type with ID = '" + source.getClassId() + "' not found");
	}

	// PRIVELEGER_BOOL s:int Признак приравненного лица
	// PRIVELEDGER_TYPE s:string Тип приравненного лица
	// PRIVELEDGER_CERTIFICATE s:string Удостоверение приравненного лица
	// PRIVELEDGER_CERTIFICATE_DATE s:string Дата выдачи удостоверения
	// приравненного лица
	final PrivilegerInfo pi = new PrivilegerInfo();
	target.setPrivilegerInfo(pi);
	target.setPrivileger(source.getPRIVELEGERBOOL() == 1);
	if (target.isPrivileger()) {
	    pi.setType(source.getPRIVELEDGERTYPE());
	    pi.setCertificateNumber(source.getPRIVELEDGERCERTIFICATE());
	    pi.setCertificateDateOfIssue(convertESBDDateToLocalDate(source.getPRIVELEDGERCERTIFICATEDATE()));
	}

	// WOW_BOOL s:int Признак участника ВОВ
	// WOW_CERTIFICATE s:string Удостоверение участника ВОВ
	// WOW_CERTIFICATE_DATE s:string Дата выдачи удостоверения участника ВОВ
	final GPWParticipantInfo gpwi = new GPWParticipantInfo();
	target.setGpwParticipantInfo(gpwi);
	target.setGpwParticipant(source.getWOWBOOL() == 1);
	if (target.isGpwParticipant()) {
	    gpwi.setCertificateNumber(source.getWOWCERTIFICATE());
	    gpwi.setCertificateDateOfIssue(convertESBDDateToLocalDate(source.getWOWCERTIFICATEDATE()));
	}

	// PENSIONER_BOOL s:int Признак пенсионера
	// PENSIONER_CERTIFICATE s:string Удостоверение пенсионера
	// PENSIONER_CERTIFICATE_DATE s:string Дата выдачи удостоверения
	// пенсионера
	final PensionerInfo pei = new PensionerInfo();
	target.setPensionerInfo(pei);
	target.setPensioner(source.getPENSIONERBOOL() == 1);
	if (target.isPensioner()) {
	    pei.setCertificateNumber(source.getPENSIONERCERTIFICATE());
	    pei.setCertiticateDateOfIssue(convertESBDDateToLocalDate(source.getPENSIONERCERTIFICATEDATE()));
	}

	// INVALID_BOOL s:int Признак инвалида
	// INVALID_CERTIFICATE s:string Удостоверение инвалида
	// INVALID_CERTIFICATE_BEG_DATE s:string Дата выдачи удостоверения
	// инвалида
	// INVALID_CERTIFICATE_END_DATE s:string Дата завершения удостоверения
	// инвалида
	final InvalidInfo ii = new InvalidInfo();
	target.setInvalidInfo(ii);
	target.setInvalid(source.getINVALIDBOOL() == 1);
	if (target.isInvalid()) {
	    ii.setCertificateNumber(source.getINVALIDCERTIFICATE());
	    ii.setCertificateValidFrom(convertESBDDateToLocalDate(source.getINVALIDCERTIFICATEBEGDATE()));
	    ii.setCertificateValidTill(convertESBDDateToLocalDate(source.getINVALIDCERTIFICATEENDDATE()));
	}

	// CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего
	// запись
	// INPUT_DATE s:string Дата\время ввода записи в систему
	final RecordOperationInfo created = new RecordOperationInfo();
	target.setCreated(created);
	created.setDate(convertESBDDateToLocalDate(source.getINPUTDATE()));
	try {
	    created.setAuthor(userService.getById(source.getCREATEDBYUSERID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching Driver ID = '" + source.getDRIVERID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Created User ID = '" + source.getCREATEDBYUSERID()
			    + "' not found",
		    e);
	}

	// RECORD_CHANGED_AT s:string Дата\время изменения записи
	// CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	// запись
	final RecordOperationInfo modified = new RecordOperationInfo();
	target.setModified(modified);
	modified.setDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()));
	if (modified.getDate() != null)
	    try {
		modified.setAuthor(userService.getById(source.getCHANGEDBYUSERID()));
	    } catch (NotFound | IllegalArgument e) {
		throw new DataCoruptionException(
			"Error while fetching Driver ID = '" + source.getDRIVERID()
				+ "' at Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. Modified User ID = '" + source.getCHANGEDBYUSERID()
				+ "' not found",
			e);
	    }

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	try {
	    target.setInsurer(insuranceCompanyService.getById(source.getSYSTEMDELIMITERID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching Driver ID = '" + source.getDRIVERID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Insurance company ID = '" + source.getSYSTEMDELIMITERID()
			    + "' not found",
		    e);
	}

    }

    void fillValues(final PoliciesTF source, final InsuredVehicleEntity target, final PolicyEntity policy) {
	// POLICY_TF_ID s:int Идентификатор ТС полиса
	target.setId(source.getPOLICYTFID());

	// POLICY_ID s:int Идентификатор полиса
	if (policy.getId() == source.getPOLICYID())
	    target.setPolicy(policy);
	else
	    try {
		target.setPolicy(getById(source.getPOLICYID()));
	    } catch (final NotFound | IllegalArgument e) {
		throw new DataCoruptionException(
			"Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
				+ "' at Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. Policty with ID = '" + source.getPOLICYID() + "' not found",
			e);
	    }

	// TF_ID s:int Идентификатор ТС
	try {
	    target.setVehicle(vehicleService.getById(source.getTFID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Vehicle with ID = '" + source.getTFID() + "' not found",
		    e);
	}

	// TF_TYPE_ID s:int Идентификатор типа ТС (обязательно)
	try {
	    target.setVehicleClass(vehicleClassService.getById(source.getTFTYPEID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. VehicleType with ID = '" + source.getTFTYPEID() + "' not found",
		    e);
	}

	// TF_AGE_ID s:int Идентификатор возраста ТС (обязательно)
	try {
	    target.setVehicleAgeClass(vehicleAgeClassService.getById(source.getTFAGEID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. VehicleAgeType with ID = '" + source.getTFAGEID() + "' not found",
		    e);
	}

	// TF_NUMBER s:string Гос. номер ТС
	// TF_REGISTRATION_CERTIFICATE s:string Номер тех. паспорта
	// GIVE_DATE s:string Дата выдачи тех. паспорта
	// REGION_ID s:int Идентификатор региона регистрации ТС (обязательно)
	// BIG_CITY_BOOL s:int Признак города областного значения (обязательно)
	final VehicleCertificateInfo vci = new VehicleCertificateInfo();
	target.setCertificate(vci);

	vci.setRegistrationNumber(source.getTFNUMBER());
	vci.setCertificateNumber(source.getTFREGISTRATIONCERTIFICATE());
	vci.setDateOfIssue(convertESBDDateToCalendar(source.getGIVEDATE()));
	vci.setMajorCity(source.getBIGCITYBOOL() == 1);
	try {
	    vci.setRegistrationRegion(countryRegionService.getById(source.getREGIONID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Vehicle Registration region with ID = '" + source.getREGIONID()
			    + "' not found",
		    e);
	}

	// PURPOSE s:string Цель использования ТС
	target.setVehiclePurpose(source.getPURPOSE());

	// ODOMETER s:int Показания одометра
	target.setCurrentOdometerValue(source.getODOMETER());

	// CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего
	// запись
	// INPUT_DATE s:string Дата\время ввода записи в систему
	final RecordOperationInfo created = new RecordOperationInfo();
	target.setCreated(created);
	created.setDate(convertESBDDateToLocalDate(source.getINPUTDATE()));
	try {
	    created.setAuthor(userService.getById(source.getCREATEDBYUSERID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Created User ID = '" + source.getCREATEDBYUSERID()
			    + "' not found",
		    e);
	}

	// RECORD_CHANGED_AT s:string Дата\время изменения записи
	// CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	// запись
	final RecordOperationInfo modified = new RecordOperationInfo();
	target.setModified(modified);
	modified.setDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()));
	if (modified.getDate() != null)
	    try {
		modified.setAuthor(userService.getById(source.getCHANGEDBYUSERID()));
	    } catch (NotFound | IllegalArgument e) {
		throw new DataCoruptionException(
			"Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
				+ "' at Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. Modified User ID = '" + source.getCHANGEDBYUSERID()
				+ "' not found",
			e);
	    }

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	try {
	    target.setInsurer(insuranceCompanyService.getById(source.getSYSTEMDELIMITERID()));
	} catch (NotFound | IllegalArgument e) {
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Insurance company ID = '" + source.getSYSTEMDELIMITERID()
			    + "' not found",
		    e);
	}
    }
}
