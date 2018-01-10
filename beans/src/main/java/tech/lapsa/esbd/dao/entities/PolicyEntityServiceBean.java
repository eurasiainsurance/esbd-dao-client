package tech.lapsa.esbd.dao.entities;

import static tech.lapsa.esbd.dao.ESBDDates.*;

import java.util.List;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.lapsa.insurance.elements.CancelationReason;
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
import tech.lapsa.esbd.dao.entities.InsuredDriverEntity.InsuredDriverEntityBuilder;
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
public class PolicyEntityServiceBean
	implements PolicyEntityServiceLocal, PolicyEntityServiceRemote {

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
	final int id = source.getPOLICYID();
	target.id = MyOptionals.of(id).orElse(null);

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
	Util.requireField(target,
		target.getId(),
		insuranceCompanyService::getById,
		target::setInsurer,
		"Insurer",
		InsuranceCompanyEntity.class,
		target._insurer);

	// CLIENT_ID s:int Идентификатор страхователя (обязательно)
	target._insurant = source.getCLIENTID();
	Util.requireField(target,
		target.getId(),
		subjectService::getById,
		target::setInsurant,
		"Insurant",
		SubjectEntity.class,
		target._insurant);

	// POLICY_DATE s:string Дата полиса
	target.dateOfIssue = convertESBDDateToLocalDate(source.getPOLICYDATE());

	// RESCINDING_DATE s:string Дата расторжения полиса
	target.dateOfCancelation = convertESBDDateToLocalDate(source.getRESCINDINGDATE());

	// RESCINDING_REASON_ID s:int Идентификатор причины расторжения
	// non mandatory field
	target._cancelationReasonType = source.getRESCINDINGREASONID();
	Util.optionalField(target,
		target.getId(),
		cancelationReasonTypeService::getById,
		target::setCancelationReasonType,
		"CancelationReasonType",
		CancelationReason.class,
		MyOptionals.of(target._cancelationReasonType));

	// BRANCH_ID s:int Филиал (обязательно)
	target._branch = source.getBRANCHID();
	Util.requireField(target,
		target.getId(),
		branchService::getById,
		target::setBranch,
		"Branch",
		BranchEntity.class,
		target._branch);

	// REWRITE_BOOL s:int Признак переоформления
	target.reissued = source.getREWRITEBOOL() == 1;

	// REWRITE_POLICY_ID s:int Ссылка на переоформляемый полис
	target._reissuedPolicy = source.getREWRITEPOLICYID();
	if (target.reissued)
	    Util.requireField(target,
		    target.getId(),
		    this::getById,
		    target::setReissuedPolicy,
		    "ReissuedPolicy",
		    PolicyEntity.class,
		    target._reissuedPolicy);

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

	{
	    // CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего
	    // полис
	    // INPUT_DATE s:string Дата\время ввода полиса в систему
	    RecordOperationInfo.builder()
		    .withDate(convertESBDDateToLocalDate(source.getINPUTDATE()))
		    .withAuthor(Util.reqField(PolicyEntity.class,
			    id,
			    userService::getById,
			    "Created.Author",
			    UserEntity.class,
			    source.getCREATEDBYUSERID()))
		    .buildTo(x -> target.created = x);
	}

	{
	    // RECORD_CHANGED_AT s:string Дата\время изменения полиса
	    // RECORD_CHANGED_AT_DATETIME s:string Дата\время изменения полиса
	    // CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	    // полис
	    RecordOperationInfo.builder()
		    .withDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()))
		    .withAuthor(Util.reqField(PolicyEntity.class,
			    id,
			    userService::getById,
			    "Modified.Author",
			    UserEntity.class,
			    source.getCHANGEDBYUSERID()))
		    .buildTo(x -> target.modified = x);
	}

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
	final InsuredDriverEntityBuilder target = InsuredDriverEntity.builder();
	fillValues(source, target, policy);
	return target.build();
    }

    void fillValues(final Driver source, final InsuredDriverEntityBuilder builder, final PolicyEntity policy) {

	final int id = source.getPOLICYID();

	{
	    // DRIVER_ID s:int Идентификатор водителя
	    builder.withId(MyOptionals.of(source.getPOLICYID()).orElse(null));
	}

	{
	    // POLICY_ID s:int Идентификатор полиса
	    final int policy_id = source.getPOLICYID();
	    if (Integer.valueOf(policy_id).equals(policy.getId()))
		builder.withPolicy(policy);
	    else
		builder.withPolicy(Util.reqField(InsuredDriverEntity.class,
			id,
			this::getById,
			"Policy",
			PolicyEntity.class,
			policy_id));
	}

	{
	    // CLIENT_ID s:int Идентификатор клиента (обязательно)
	    builder.withInsuredPerson(Util.reqField(InsuredDriverEntity.class,
		    id,
		    subjectPersonService::getById,
		    "InsuredPerson",
		    SubjectPersonEntity.class,
		    source.getCLIENTID()));
	}

	{
	    // HOUSEHOLD_POSITION_ID s:int Идентификатор семейного положения
	    builder.withMaritalStatus(Util.reqField(InsuredDriverEntity.class,
		    id,
		    maritalStatusService::getById,
		    "MaritalStatus",
		    MaritalStatus.class,
		    source.getHOUSEHOLDPOSITIONID()));
	}

	{
	    // AGE_EXPERIENCE_ID s:int Идентификатор возраста\стажа вождения
	    builder.withInsuredAgeExpirienceClass(Util.reqField(InsuredDriverEntity.class,
		    id,
		    driverExpirienceClassificationService::getById,
		    "InsuredAgeExpirienceClass",
		    InsuredAgeAndExpirienceClass.class,
		    source.getAGEEXPERIENCEID()));
	}

	{
	    // EXPERIENCE s:int Стаж вождения
	    builder.withDrivingExpirience(source.getEXPERIENCE());
	}

	{
	    // DRIVER_CERTIFICATE s:string Номер водительского удостоверения
	    // DRIVER_CERTIFICATE_DATE s:string Дата выдачи водительского
	    // удостоверения
	    DriverLicenseInfo.builder() //
		    .withDateOfIssue(convertESBDDateToLocalDate(source.getDRIVERCERTIFICATEDATE())) //
		    .withNumber(source.getDRIVERCERTIFICATE()) //
		    .buildTo(builder::withDriverLicense);
	}

	{
	    // getClassId
	    final int class_id = source.getClassId();
	    builder.withInsuraceClassType(Util.reqField(InsuredDriverEntity.class,
		    id,
		    insuranceClassTypeService::getById,
		    "InsuraceClassType",
		    InsuranceClassType.class,
		    class_id));
	}

	{
	    // PRIVELEGER_BOOL s:int Признак приравненного лица
	    // PRIVELEDGER_TYPE s:string Тип приравненного лица
	    // PRIVELEDGER_CERTIFICATE s:string Удостоверение приравненного лица
	    // PRIVELEDGER_CERTIFICATE_DATE s:string Дата выдачи удостоверения
	    // приравненного лица
	    final boolean privileger = source.getPRIVELEGERBOOL() == 1;
	    if (privileger)
		PrivilegerInfo.builder() //
			.withType(source.getPRIVELEDGERTYPE())
			.withCertificateNumber(source.getPRIVELEDGERCERTIFICATE()) //
			.withCertificateDateOfIssue(convertESBDDateToLocalDate(source.getPRIVELEDGERCERTIFICATEDATE())) //
			.buildTo(builder::withPrivilegerInfo);
	}

	{
	    // WOW_BOOL s:int Признак участника ВОВ
	    // WOW_CERTIFICATE s:string Удостоверение участника ВОВ
	    // WOW_CERTIFICATE_DATE s:string Дата выдачи удостоверения участника
	    // ВОВ
	    final boolean gpwParticipant = source.getWOWBOOL() == 1;
	    if (gpwParticipant)
		GPWParticipantInfo.builder() //
			.withCertificateDateOfIssue(convertESBDDateToLocalDate(source.getWOWCERTIFICATEDATE())) //
			.withCertificateNumber(source.getWOWCERTIFICATE()) //
			.buildTo(builder::withGpwParticipantInfo);
	}

	{
	    // PENSIONER_BOOL s:int Признак пенсионера
	    // PENSIONER_CERTIFICATE s:string Удостоверение пенсионера
	    // PENSIONER_CERTIFICATE_DATE s:string Дата выдачи удостоверения
	    // пенсионера
	    final boolean pensioner = source.getPENSIONERBOOL() == 1;
	    if (pensioner)
		PensionerInfo.builder() //
			.withCertificateNumber(source.getPENSIONERCERTIFICATE()) //
			.withCertiticateDateOfIssue(convertESBDDateToLocalDate(source.getPENSIONERCERTIFICATEDATE())) //
			.buildTo(builder::withPensionerInfo);
	}

	{
	    // INVALID_BOOL s:int Признак инвалида
	    // INVALID_CERTIFICATE s:string Удостоверение инвалида
	    // INVALID_CERTIFICATE_BEG_DATE s:string Дата выдачи удостоверения
	    // инвалида
	    // INVALID_CERTIFICATE_END_DATE s:string Дата завершения
	    // удостоверения
	    // инвалида
	    final boolean handicapped = source.getINVALIDBOOL() == 1;
	    if (handicapped)
		HandicappedInfo.builder() //
			.withCertificateNumber(source.getINVALIDCERTIFICATE()) //
			.withCertificateValidFrom(convertESBDDateToLocalDate(source.getINVALIDCERTIFICATEBEGDATE())) //
			.withCertificateValidTill(convertESBDDateToLocalDate(source.getINVALIDCERTIFICATEENDDATE())) //
			.buildTo(builder::withHandicappedInfo);
	}

	{
	    // CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего
	    // запись
	    // INPUT_DATE s:string Дата\время ввода записи в систему
	    RecordOperationInfo.builder()
		    .withDate(convertESBDDateToLocalDate(source.getINPUTDATE()))
		    .withAuthor(Util.reqField(InsuredDriverEntity.class,
			    id,
			    userService::getById,
			    "Created.Author",
			    UserEntity.class,
			    source.getCREATEDBYUSERID()))
		    .buildTo(builder::withCreated);
	}

	{
	    // RECORD_CHANGED_AT s:string Дата\время изменения записи
	    // CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	    // запись
	    RecordOperationInfo.builder()
		    .withDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()))
		    .withAuthor(Util.reqField(InsuredDriverEntity.class,
			    id,
			    userService::getById,
			    "Modified.Author",
			    UserEntity.class,
			    source.getCHANGEDBYUSERID()))
		    .buildTo(builder::withModified);
	}

	{
	    // SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	    builder.withInsurer(Util.reqField(InsuredDriverEntity.class,
		    id,
		    insuranceCompanyService::getById,
		    "Insurer",
		    InsuranceCompanyEntity.class,
		    source.getSYSTEMDELIMITERID()));
	}
    }

    InsuredVehicleEntity convert(final PoliciesTF source, final PolicyEntity policy) {
	final InsuredVehicleEntity target = new InsuredVehicleEntity();
	fillValues(source, target, policy);
	return target;
    }

    void fillValues(final PoliciesTF source, final InsuredVehicleEntity target, final PolicyEntity policy) {

	final int id = source.getPOLICYTFID();

	{
	    // POLICY_TF_ID s:int Идентификатор ТС полиса
	    target.id = MyOptionals.of(id).orElse(null);
	}

	target._policy = source.getPOLICYID();
	// POLICY_ID s:int Идентификатор полиса
	if (Integer.valueOf(target._policy).equals(policy.getId()))
	    target.policy = policy;
	else
	    Util.requireField(target,
		    target.getId(),
		    this::getById,
		    target::setPolicy,
		    "Policy",
		    PolicyEntity.class,
		    target._policy);

	// TF_ID s:int Идентификатор ТС
	target._vehicle = source.getTFID();
	Util.requireField(target,
		target.getId(),
		vehicleService::getById,
		target::setVehicle,
		"Vehicle",
		VehicleEntity.class,
		target._vehicle);

	// TF_TYPE_ID s:int Идентификатор типа ТС (обязательно)
	target._vehicleClass = source.getTFTYPEID();
	Util.requireField(target,
		target.getId(),
		vehicleClassService::getById,
		target::setVehicleClass,
		"VehicleClass",
		VehicleClass.class,
		target._vehicleClass);

	// TF_AGE_ID s:int Идентификатор возраста ТС (обязательно)
	target._vehicleAgeClass = source.getTFAGEID();
	Util.requireField(target,
		target.getId(),
		vehicleAgeClassService::getById,
		target::setVehicleAgeClass,
		"VehicleAgeClass",
		VehicleAgeClass.class,
		target._vehicleAgeClass);

	{
	    // TF_NUMBER s:string Гос. номер ТС
	    // TF_REGISTRATION_CERTIFICATE s:string Номер тех. паспорта
	    // GIVE_DATE s:string Дата выдачи тех. паспорта
	    // REGION_ID s:int Идентификатор региона регистрации ТС
	    // (обязательно)
	    // BIG_CITY_BOOL s:int Признак города областного значения
	    // (обязательно)
	    VehicleCertificateInfo.builder() //
		    .withCertificateNumber(source.getTFREGISTRATIONCERTIFICATE())
		    .withDateOfIssue(convertESBDDateToLocalDate(source.getGIVEDATE()))
		    .withRegistrationMajorCity(source.getBIGCITYBOOL() == 1)
		    .withRegistrationRegion(Util.reqField(InsuredVehicleEntity.class,
			    id,
			    countryRegionService::getById,
			    "Certificate.RegistrationRegion",
			    KZArea.class,
			    source.getREGIONID()))
		    .withRegistrationNumber(source.getTFNUMBER())
		    .buildTo(builder::withCertificate);
	}

	// PURPOSE s:string Цель использования ТС
	target.vehiclePurpose = source.getPURPOSE();

	// ODOMETER s:int Показания одометра
	target.currentOdometerValue = source.getODOMETER();

	// CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего
	// запись
	// INPUT_DATE s:string Дата\время ввода записи в систему
	RecordOperationInfo.builder()
		.withDate(convertESBDDateToLocalDate(source.getINPUTDATE()))
		.withAuthor(Util.reqField(InsuredVehicleEntity.class,
			id,
			userService::getById,
			"Created.Author",
			UserEntity.class,
			source.getCREATEDBYUSERID()))
		.buildTo(x -> target.created = x);

	// RECORD_CHANGED_AT s:string Дата\время изменения записи
	// CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего
	// запись
	RecordOperationInfo.builder()
		.withDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()))
		.withAuthor(Util.reqField(InsuredVehicleEntity.class,
			id,
			userService::getById,
			"Modified.Author",
			UserEntity.class,
			source.getCHANGEDBYUSERID()))
		.buildTo(x -> target.modified = x);

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	target._insurer = source.getSYSTEMDELIMITERID();
	Util.requireField(target,
		target.getId(),
		insuranceCompanyService::getById,
		target::setInsurer, "Insurer",
		InsuranceCompanyEntity.class,
		target._insurer);
    }
}
