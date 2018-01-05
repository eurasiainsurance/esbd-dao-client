package tech.lapsa.esbd.dao.entities;

import static tech.lapsa.esbd.dao.beans.ESBDDates.*;

import java.util.List;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;
import com.lapsa.insurance.elements.MaritalStatus;
import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.BranchEntityService.BranchEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.elements.CancelationReasonService.CancelationReasonServiceLocal;
import tech.lapsa.esbd.dao.elements.InsuranceClassTypeService.InsuranceClassTypeServiceLocal;
import tech.lapsa.esbd.dao.elements.InsuredAgeAndExpirienceClassService.InsuredAgeAndExpirienceClassServiceLocal;
import tech.lapsa.esbd.dao.elements.KZAreaService.KZAreaServiceLocal;
import tech.lapsa.esbd.dao.elements.MaritalStatusService.MaritalStatusServiceLocal;
import tech.lapsa.esbd.dao.elements.VehicleAgeClassService.VehicleAgeClassServiceLocal;
import tech.lapsa.esbd.dao.elements.VehicleClassService.VehicleClassServiceLocal;
import tech.lapsa.esbd.dao.entities.PolicyEntityService.PolicyEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.PolicyEntityService.PolicyEntityServiceRemote;
import tech.lapsa.esbd.dao.entities.SubjectEntityService.SubjectEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntityService.SubjectPersonEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.UserEntityService.UserEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleEntityService.VehicleEntityServiceLocal;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfDriver;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfPoliciesTF;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfPolicy;
import tech.lapsa.esbd.jaxws.wsimport.Driver;
import tech.lapsa.esbd.jaxws.wsimport.PoliciesTF;
import tech.lapsa.esbd.jaxws.wsimport.Policy;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.logging.MyLogger;

@Stateless(name = PolicyEntityService.BEAN_NAME)
public class PolicyEntityServiceBean implements PolicyEntityServiceLocal, PolicyEntityServiceRemote {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(PolicyEntityService.class) //
	    .build();

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public PolicyEntity getById(final Integer id) throws NotFound, IllegalArgument {
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
    public PolicyEntity getByNumber(final String number) throws NotFound, IllegalArgument {
	try {
	    return _getByNumber(number);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PolicyEntity> getByInternalNumber(final String internalNumber) throws IllegalArgument {
	try {
	    return _getByInternalNumber(internalNumber);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

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

    private PolicyEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	final Policy source;
	try (Connection con = pool.getConnection()) {
	    source = con.getPolicyByID(id);
	}
	if (source == null)
	    throw new NotFound(PolicyEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	return convert(source);
    }

    private PolicyEntity _getByNumber(final String number) throws IllegalArgumentException, NotFound {
	MyStrings.requireNonEmpty(number, "number");

	final Policy source;
	try (Connection con = pool.getConnection()) {
	    source = con.getPolicyByGlobalID(number);
	}
	if (MyObjects.isNull(source))
	    throw new NotFound(PolicyEntity.class.getSimpleName() + " not found with NUMBER = '" + number + "'");
	return convert(source);
    }

    private List<PolicyEntity> _getByInternalNumber(final String internalNumber) throws IllegalArgumentException {
	MyStrings.requireNonEmpty(internalNumber, "internalNumber");

	final ArrayOfPolicy policies;
	try (Connection con = pool.getConnection()) {
	    policies = con.getPoliciesByNumber(internalNumber);
	}

	return MyOptionals.of(policies) //
		.map(ArrayOfPolicy::getPolicy) //
		.map(List::stream) //
		.orElseGet(Stream::empty) //
		.map(this::convert) //
		.collect(MyCollectors.unmodifiableList());
    }

    PolicyEntity convert(final Policy source) {
	final PolicyEntity policy = new PolicyEntity();
	fillValues(source, policy);
	return policy;
    }

    void fillValues(final Policy source, final PolicyEntity target) {
	// POLICY_ID s:int Идентификатор полиса (обязательно)
	target.id = MyOptionals.of(source.getPOLICYID()).orElse(null);

	// GLOBAL_ID s:string Уникальный глобальный идентификатор полиса
	target.number = source.getGLOBALID();

	// POLICY_NUMBER s:string Номер полиса (обязательно)
	target.internalNumber = source.getPOLICYNUMBER();

	// DATE_BEG s:string Дата начала действия полиса (обязательно)
	target.validFrom = convertESBDDateToLocalDate(source.getDATEBEG());

	// DATE_END s:string Дата окончания действия полиса (обязательно)
	target.validTill = convertESBDDateToLocalDate(source.getDATEEND());

	// PREMIUM s:double Страховая премия (обязательно)
	target.actualPremium = MyOptionals.of(source.getPREMIUM()).orElse(null);

	// CALCULATED_PREMIUM s:double Страховая премия рассчитанная системой
	target.calculatedPremium = MyOptionals.of(source.getCALCULATEDPREMIUM()).orElse(null);

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	target._insurer = source.getSYSTEMDELIMITERID();
	Util.requireField(target, target.getId(), insuranceCompanyService::getById,
		target::setInsurer, "Insurer", InsuranceCompanyEntity.class, target._insurer);

	// CLIENT_ID s:int Идентификатор страхователя (обязательно)
	target._insurant = source.getCLIENTID();
	Util.requireField(target, target.getId(), subjectService::getById, target::setInsurant,
		"Insurant", SubjectEntity.class, target._insurant);

	// POLICY_DATE s:string Дата полиса
	target.dateOfIssue = convertESBDDateToLocalDate(source.getPOLICYDATE());

	// RESCINDING_DATE s:string Дата расторжения полиса
	target.dateOfCancelation = convertESBDDateToLocalDate(source.getRESCINDINGDATE());

	// RESCINDING_REASON_ID s:int Идентификатор причины расторжения
	// non mandatory field
	target._cancelationReasonType = source.getRESCINDINGREASONID();
	target.cancelationReasonType = MyOptionals.of(target._cancelationReasonType) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> cancelationReasonTypeService.getById(id))) //
		.orElse(null);

	// BRANCH_ID s:int Филиал (обязательно)
	target._branch = source.getBRANCHID();
	Util.requireField(target, target.getId(), branchService::getById, target::setBranch, "Branch",
		BranchEntity.class, target._branch);

	// REWRITE_BOOL s:int Признак переоформления
	target.reissued = source.getREWRITEBOOL() == 1;

	// REWRITE_POLICY_ID s:int Ссылка на переоформляемый полис
	target._reissuedPolicy = source.getREWRITEPOLICYID();
	if (target.reissued)
	    Util.requireField(target, target.getId(), this::getById, target::setReissuedPolicy,
		    "ReissuedPolicy", PolicyEntity.class, target._reissuedPolicy);

	// DESCRIPTION s:string Комментарии к полису
	target.comments = source.getDESCRIPTION();

	// Drivers tns:ArrayOfDriver Водители (обязательно)
	MyOptionals.of(source) //
		.map(Policy::getDrivers) //
		.map(ArrayOfDriver::getDriver) //
		.map(List::stream) //
		.orElseThrow(() -> Util.requireNonEmtyList(target, target.getId(), "InsuredDrivers")) //
		.map(x -> this.convert(x, target)) //
		.forEach(target::addDriver);

	// PoliciesTF tns:ArrayOfPolicies_TF Транспортные средства полиса
	// (обязательно)
	MyOptionals.of(source) //
		.map(Policy::getPoliciesTF) //
		.map(ArrayOfPoliciesTF::getPoliciesTF) //
		.map(List::stream) //
		.orElseThrow(() -> Util.requireNonEmtyList(target, target.getId(), "InsuredVehicles")) //
		.map(x -> this.convert(x, target)) //
		.forEach(target::addVehicle);

	// CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего полис
	// INPUT_DATE s:string Дата\время ввода полиса в систему
	target.created = new RecordOperationInfo();
	target.created.date = convertESBDDateToLocalDate(source.getINPUTDATE());
	target.created._author = source.getCREATEDBYUSERID();
	Util.requireField(target, target.getId(), userService::getById, target.created::setAuthor,
		"Created.Author", UserEntity.class, target.created._author);

	// RECORD_CHANGED_AT s:string Дата\время изменения полиса
	// RECORD_CHANGED_AT_DATETIME s:string Дата\время изменения полиса
	// CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	// полис
	target.modified = new RecordOperationInfo();
	target.modified.date = convertESBDDateToLocalDate(source.getRECORDCHANGEDAT());
	target.modified._author = source.getCHANGEDBYUSERID();
	Util.optionalField(target, target.getId(), userService::getById, target.modified::setAuthor,
		"Modified.Author", UserEntity.class, MyOptionals.of(target.modified._author));

	// ScheduledPayments tns:ArrayOfSCHEDULED_PAYMENT Плановые платежи по
	// полису
	// PAYMENT_ORDER_TYPE_ID s:int Порядок оплаты (Идентификатор)
	// PAYMENT_ORDER_TYPE s:string Порядок оплаты
	// PAYMENT_DATE s:string Дата оплаты
	// MIDDLEMAN_ID s:int Посредник (Идентификатор)
	// MIDDLEMAN_CONTRACT_NUMBER s:string Номер договора посредника
	// CLIENT_FORM_ID s:int Форма клиента (справочник CLIENT_FORMS)
    }

    InsuredDriverEntity convert(final Driver source, final PolicyEntity policy) {
	final InsuredDriverEntity target = new InsuredDriverEntity();
	fillValues(source, target, policy);
	return target;
    }

    void fillValues(final Driver source, final InsuredDriverEntity target, final PolicyEntity policy) {
	// DRIVER_ID s:int Идентификатор водителя
	target.id = MyOptionals.of(source.getPOLICYID()).orElse(null);

	target._policy = source.getPOLICYID();
	// POLICY_ID s:int Идентификатор полиса
	if (Integer.valueOf(target._policy).equals(policy.getId()))
	    target.policy = policy;
	else
	    Util.requireField(target, target.getId(), this::getById, target::setPolicy, "Policy",
		    PolicyEntity.class, target._policy);

	// CLIENT_ID s:int Идентификатор клиента (обязательно)
	target._insuredPerson = source.getCLIENTID();
	Util.requireField(target, target.id, subjectPersonService::getById,
		target::setInsuredPerson, "InsuredPerson",
		SubjectPersonEntity.class, target._insuredPerson);

	// HOUSEHOLD_POSITION_ID s:int Идентификатор семейного положения
	target._maritalStatus = source.getHOUSEHOLDPOSITIONID();
	Util.requireField(target, target.id, maritalStatusService::getById,
		target::setMaritalStatus, "MaritalStatus",
		MaritalStatus.class, target._maritalStatus);

	// AGE_EXPERIENCE_ID s:int Идентификатор возраста\стажа вождения
	target._insuredAgeExpirienceClass = source.getAGEEXPERIENCEID();
	Util.requireField(target, target.id, driverExpirienceClassificationService::getById,
		target::setInsuredAgeExpirienceClass, "InsuredAgeExpirienceClass", InsuredAgeAndExpirienceClass.class,
		target._insuredAgeExpirienceClass);

	// EXPERIENCE s:int Стаж вождения
	target.drivingExpirience = source.getEXPERIENCE();

	// DRIVER_CERTIFICATE s:string Номер водительского удостоверения
	// DRIVER_CERTIFICATE_DATE s:string Дата выдачи водительского
	// удостоверения
	target.driverLicense = new DriverLicenseInfo();
	target.driverLicense.number = source.getDRIVERCERTIFICATE();
	target.driverLicense.dateOfIssue = convertESBDDateToLocalDate(source.getDRIVERCERTIFICATEDATE());

	// getClassId
	target._insuraceClassType = source.getClassId();
	Util.requireField(target, target.id, insuranceClassTypeService::getById, target::setInsuraceClassType,
		"InsuraceClassType", InsuranceClassType.class, target._insuraceClassType);

	// PRIVELEGER_BOOL s:int Признак приравненного лица
	// PRIVELEDGER_TYPE s:string Тип приравненного лица
	// PRIVELEDGER_CERTIFICATE s:string Удостоверение приравненного лица
	// PRIVELEDGER_CERTIFICATE_DATE s:string Дата выдачи удостоверения
	// приравненного лица
	target.privilegerInfo = new PrivilegerInfo();
	target.privileger = source.getPRIVELEGERBOOL() == 1;
	if (target.privileger) {
	    target.privilegerInfo.type = source.getPRIVELEDGERTYPE();
	    target.privilegerInfo.certificateNumber = source.getPRIVELEDGERCERTIFICATE();
	    target.privilegerInfo.certificateDateOfIssue = convertESBDDateToLocalDate(
		    source.getPRIVELEDGERCERTIFICATEDATE());
	}

	// WOW_BOOL s:int Признак участника ВОВ
	// WOW_CERTIFICATE s:string Удостоверение участника ВОВ
	// WOW_CERTIFICATE_DATE s:string Дата выдачи удостоверения участника ВОВ
	target.gpwParticipantInfo = new GPWParticipantInfo();
	target.gpwParticipant = source.getWOWBOOL() == 1;
	if (target.gpwParticipant) {
	    target.gpwParticipantInfo.certificateNumber = source.getWOWCERTIFICATE();
	    target.gpwParticipantInfo.certificateDateOfIssue = convertESBDDateToLocalDate(
		    source.getWOWCERTIFICATEDATE());
	}

	// PENSIONER_BOOL s:int Признак пенсионера
	// PENSIONER_CERTIFICATE s:string Удостоверение пенсионера
	// PENSIONER_CERTIFICATE_DATE s:string Дата выдачи удостоверения
	// пенсионера
	target.pensionerInfo = new PensionerInfo();
	target.pensioner = source.getPENSIONERBOOL() == 1;
	if (target.pensioner) {
	    target.pensionerInfo.certificateNumber = source.getPENSIONERCERTIFICATE();
	    target.pensionerInfo.certiticateDateOfIssue = convertESBDDateToLocalDate(
		    source.getPENSIONERCERTIFICATEDATE());
	}

	// INVALID_BOOL s:int Признак инвалида
	// INVALID_CERTIFICATE s:string Удостоверение инвалида
	// INVALID_CERTIFICATE_BEG_DATE s:string Дата выдачи удостоверения
	// инвалида
	// INVALID_CERTIFICATE_END_DATE s:string Дата завершения удостоверения
	// инвалида
	target.invalidInfo = new InvalidInfo();
	target.invalid = source.getINVALIDBOOL() == 1;
	if (target.invalid) {
	    target.invalidInfo.certificateNumber = source.getINVALIDCERTIFICATE();
	    target.invalidInfo.certificateValidFrom = convertESBDDateToLocalDate(source.getINVALIDCERTIFICATEBEGDATE());
	    target.invalidInfo.certificateValidTill = convertESBDDateToLocalDate(source.getINVALIDCERTIFICATEENDDATE());
	}

	// CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего
	// запись
	// INPUT_DATE s:string Дата\время ввода записи в систему
	target.created = new RecordOperationInfo();
	target.created.date = convertESBDDateToLocalDate(source.getINPUTDATE());
	target.created._author = source.getCREATEDBYUSERID();
	Util.requireField(target, target.id, userService::getById, target.created::setAuthor,
		"Created.Author", UserEntity.class, target.created._author);

	// RECORD_CHANGED_AT s:string Дата\время изменения записи
	// CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	// запись
	target.modified = new RecordOperationInfo();
	target.modified.date = convertESBDDateToLocalDate(source.getRECORDCHANGEDAT());
	target.modified._author = source.getCHANGEDBYUSERID();
	Util.optionalField(target, target.getId(), userService::getById, target.modified::setAuthor,
		"Modified.Author", UserEntity.class, MyOptionals.of(target.modified._author));

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	target._insurer = source.getSYSTEMDELIMITERID();
	Util.requireField(target, target.id, insuranceCompanyService::getById, target::setInsurer,
		"Insurer", InsuranceCompanyEntity.class, target._insurer);
    }

    InsuredVehicleEntity convert(final PoliciesTF source, final PolicyEntity policy) {
	final InsuredVehicleEntity target = new InsuredVehicleEntity();
	fillValues(source, target, policy);
	return target;
    }

    void fillValues(final PoliciesTF source, final InsuredVehicleEntity target, final PolicyEntity policy) {
	// POLICY_TF_ID s:int Идентификатор ТС полиса
	target.id = MyOptionals.of(source.getPOLICYTFID()).orElse(null);

	target._policy = source.getPOLICYID();
	// POLICY_ID s:int Идентификатор полиса
	if (Integer.valueOf(target._policy).equals(policy.getId()))
	    target.policy = policy;
	else
	    Util.requireField(target, target.getId(), this::getById, target::setPolicy, "Policy",
		    PolicyEntity.class, target._policy);

	// TF_ID s:int Идентификатор ТС
	target._vehicle = source.getTFID();
	Util.requireField(target, target.getId(), vehicleService::getById, target::setVehicle, "Vehicle",
		VehicleEntity.class, target._vehicle);

	// TF_TYPE_ID s:int Идентификатор типа ТС (обязательно)
	target._vehicleClass = source.getTFTYPEID();
	Util.requireField(target, target.getId(), vehicleClassService::getById, target::setVehicleClass,
		"VehicleClass", VehicleClass.class, target._vehicleClass);

	// TF_AGE_ID s:int Идентификатор возраста ТС (обязательно)
	target._vehicleAgeClass = source.getTFAGEID();
	Util.requireField(target, target.getId(), vehicleAgeClassService::getById,
		target::setVehicleAgeClass, "VehicleAgeClass", VehicleAgeClass.class, target._vehicleAgeClass);

	// TF_NUMBER s:string Гос. номер ТС
	// TF_REGISTRATION_CERTIFICATE s:string Номер тех. паспорта
	// GIVE_DATE s:string Дата выдачи тех. паспорта
	// REGION_ID s:int Идентификатор региона регистрации ТС (обязательно)
	// BIG_CITY_BOOL s:int Признак города областного значения (обязательно)
	target.certificate = new VehicleCertificateInfo();
	target.certificate.registrationNumber = source.getTFNUMBER();
	target.certificate.certificateNumber = source.getTFREGISTRATIONCERTIFICATE();
	target.certificate.dateOfIssue = convertESBDDateToLocalDate(source.getGIVEDATE());
	target.certificate.registrationMajorCity = source.getBIGCITYBOOL() == 1;
	target.certificate._registrationRegion = source.getREGIONID();
	Util.requireField(target, target.getId(), countryRegionService::getById,
		target.certificate::setRegistrationRegion, "Certificate.RegistrationRegion", KZArea.class,
		target.certificate._registrationRegion);

	// PURPOSE s:string Цель использования ТС
	target.vehiclePurpose = source.getPURPOSE();

	// ODOMETER s:int Показания одометра
	target.currentOdometerValue = source.getODOMETER();

	// CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего
	// запись
	// INPUT_DATE s:string Дата\время ввода записи в систему
	target.created = new RecordOperationInfo();
	target.created.date = convertESBDDateToLocalDate(source.getINPUTDATE());
	target.created._author = source.getCREATEDBYUSERID();
	Util.requireField(target, target.getId(), userService::getById, target.created::setAuthor,
		"Created.Author", UserEntity.class, target.created._author);

	// RECORD_CHANGED_AT s:string Дата\время изменения записи
	// CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	// запись
	target.modified = new RecordOperationInfo();
	target.modified.date = convertESBDDateToLocalDate(source.getRECORDCHANGEDAT());
	target.modified._author = source.getCHANGEDBYUSERID();
	Util.optionalField(target, target.getId(), userService::getById, target.modified::setAuthor,
		"Modified.Author", UserEntity.class, MyOptionals.of(target.modified._author));

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	target._insurer = source.getSYSTEMDELIMITERID();
	Util.requireField(target, target.getId(), insuranceCompanyService::getById, target::setInsurer, "Insurer",
		InsuranceCompanyEntity.class, target._insurer);
    }
}
