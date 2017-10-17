package com.lapsa.insurance.esbd.services.impl.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.ws.soap.SOAPFaultException;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfDriver;
import com.lapsa.esbd.jaxws.client.ArrayOfPoliciesTF;
import com.lapsa.esbd.jaxws.client.ArrayOfPolicy;
import com.lapsa.esbd.jaxws.client.Driver;
import com.lapsa.esbd.jaxws.client.PoliciesTF;
import com.lapsa.esbd.jaxws.client.Policy;
import com.lapsa.insurance.esbd.domain.entities.policy.InsuredDriverEntity;
import com.lapsa.insurance.esbd.domain.entities.policy.InsuredVehicleEntity;
import com.lapsa.insurance.esbd.domain.entities.policy.PolicyEntity;
import com.lapsa.insurance.esbd.domain.infos.general.RecordOperationInfo;
import com.lapsa.insurance.esbd.domain.infos.general.VehicleCertificateInfo;
import com.lapsa.insurance.esbd.domain.infos.policy.DriverLicenseInfo;
import com.lapsa.insurance.esbd.domain.infos.policy.GPWParticipantInfo;
import com.lapsa.insurance.esbd.domain.infos.policy.InvalidInfo;
import com.lapsa.insurance.esbd.domain.infos.policy.PensionerInfo;
import com.lapsa.insurance.esbd.domain.infos.policy.PrivilegerInfo;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.CancelationReasonServiceDAO;
import com.lapsa.insurance.esbd.services.elements.InsuranceClassTypeServiceDAO;
import com.lapsa.insurance.esbd.services.elements.InsuredAgeAndExpirienceClassServiceDAO;
import com.lapsa.insurance.esbd.services.elements.KZAreaServiceDAO;
import com.lapsa.insurance.esbd.services.elements.MaritalStatusServiceDAO;
import com.lapsa.insurance.esbd.services.elements.VehicleAgeClassServiceDAO;
import com.lapsa.insurance.esbd.services.elements.VehicleClassServiceDAO;
import com.lapsa.insurance.esbd.services.general.BranchServiceDAO;
import com.lapsa.insurance.esbd.services.general.InsuranceCompanyServiceDAO;
import com.lapsa.insurance.esbd.services.general.SubjectPersonServiceDAO;
import com.lapsa.insurance.esbd.services.general.SubjectServiceDAO;
import com.lapsa.insurance.esbd.services.general.UserServiceDAO;
import com.lapsa.insurance.esbd.services.policy.PolicyServiceDAO;
import com.lapsa.insurance.esbd.services.policy.VehicleServiceDAO;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class PolicyEntityServiceWS extends AbstractESBDEntityServiceWS implements PolicyServiceDAO {

    @EJB
    private InsuranceCompanyServiceDAO insuranceCompanyService;

    @EJB
    private SubjectServiceDAO subjectService;

    @EJB
    private CancelationReasonServiceDAO cancelationReasonTypeService;

    @EJB
    private BranchServiceDAO branchService;

    @EJB
    private InsuredAgeAndExpirienceClassServiceDAO driverExpirienceClassificationService;

    @EJB
    private InsuranceClassTypeServiceDAO insuranceClassTypeService;

    @EJB
    private SubjectPersonServiceDAO subjectPersonService;

    @EJB
    private MaritalStatusServiceDAO maritalStatusService;

    @EJB
    private UserServiceDAO userService;

    @EJB
    private VehicleServiceDAO vehicleService;

    @EJB
    private VehicleClassServiceDAO vehicleClassService;

    @EJB
    private VehicleAgeClassServiceDAO vehicleAgeClassService;

    @EJB
    private KZAreaServiceDAO countryRegionService;

    @Inject
    private ESBDConnectionPool pool;

    @Override
    public PolicyEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "ID must be not null");
	try (ESBDConnection con = pool.getConnection()) {
	    Policy source = null;
	    try {
		source = con.getPolicyByID(new Long(id).intValue());
	    } catch (SOAPFaultException e) {
		source = null;
	    }
	    if (source == null)
		throw new NotFound(PolicyEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    PolicyEntity target = new PolicyEntity();
	    fillValues(source, target);
	    return target;
	}
    }

    @Override
    public PolicyEntity getByNumber(String number) throws NotFound {
	MyStrings.requireNonEmpty(number, "number");

	try (ESBDConnection con = pool.getConnection()) {
	    ArrayOfPolicy policies = con.getPoliciesByNumber(number);
	    if (policies == null || policies.getPolicy() == null || policies.getPolicy().isEmpty())
		throw new NotFound(PolicyEntity.class.getSimpleName() + " not found with NUMBER = '" + number + "'");
	    if (policies.getPolicy().size() > 1)
		throw new DataCoruptionException(
			"Too many " + PolicyEntity.class.getSimpleName() + " ("
				+ policies.getPolicy().size() + ") with NUMBER = '" + number + "'");

	    PolicyEntity policy = new PolicyEntity();
	    fillValues(policies.getPolicy().iterator().next(), policy);
	    return policy;
	}
    }

    void fillValues(Policy source, PolicyEntity target) {
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
	    target.setInsurer(insuranceCompanyService.getById(new Long(source.getSYSTEMDELIMITERID())));
	} catch (NotFound e) {
	    // mandatory field
	    throw new DataCoruptionException(
		    "Error while fetching Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Insurance company ID = '" + source.getSYSTEMDELIMITERID()
			    + "' not found",
		    e);
	}

	// CLIENT_ID s:int Идентификатор страхователя (обязательно)
	try {
	    target.setInsurant(subjectService.getById(new Long(source.getCLIENTID())));
	} catch (NotFound e) {
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
	try {
	    if (MyNumbers.nonZero(source.getRESCINDINGREASONID()))
		target.setCancelationReasonType(
			cancelationReasonTypeService.getById(source.getRESCINDINGREASONID()));
	} catch (NotFound e) {
	    // non mandatory field
	}

	// BRANCH_ID s:int Филиал (обязательно)
	try {
	    target.setBranch(branchService.getById(new Long(source.getBRANCHID())));
	} catch (NotFound e) {
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
	ArrayOfDriver drivers = source.getDrivers();
	if (drivers == null || drivers.getDriver() == null || drivers.getDriver().isEmpty())
	    throw new DataCoruptionException(
		    "Error while fetching Policy ID = '" + source.getPOLICYID() + "' from ESBD. Drivers list is empty");
	List<InsuredDriverEntity> insuredDrivers = new ArrayList<>();
	target.setInsuredDrivers(insuredDrivers);
	for (Driver s : drivers.getDriver()) {
	    InsuredDriverEntity t = new InsuredDriverEntity();
	    fillValues(s, t, target);
	    insuredDrivers.add(t);
	}

	// PoliciesTF tns:ArrayOfPolicies_TF Транспортные средства полиса
	// (обязательно)
	ArrayOfPoliciesTF vehicles = source.getPoliciesTF();
	if (vehicles == null || vehicles.getPoliciesTF() == null || vehicles.getPoliciesTF().isEmpty())
	    throw new DataCoruptionException(
		    "Error while fetching Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Insured vehicle list is empty");
	List<InsuredVehicleEntity> insuredVehicles = new ArrayList<>();
	target.setInsuredVehicles(insuredVehicles);
	for (PoliciesTF s : vehicles.getPoliciesTF()) {
	    InsuredVehicleEntity t = new InsuredVehicleEntity();
	    fillValues(s, t, target);
	    insuredVehicles.add(t);
	}

	// CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего полис
	// INPUT_DATE s:string Дата\время ввода полиса в систему
	RecordOperationInfo created = new RecordOperationInfo();
	target.setCreated(created);
	created.setDate(convertESBDDateToLocalDate(source.getINPUTDATE()));
	try {
	    created.setAuthor(userService.getById(new Long(source.getCREATEDBYUSERID())));
	} catch (NotFound e) {
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
	RecordOperationInfo modified = new RecordOperationInfo();
	target.setModified(modified);
	modified.setDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()));
	if (modified.getDate() != null)
	    try {
		modified.setAuthor(userService.getById(new Long(source.getCHANGEDBYUSERID())));
	    } catch (NotFound e) {
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

    void fillValues(Driver source, InsuredDriverEntity target, PolicyEntity policy) {
	// DRIVER_ID s:int Идентификатор водителя
	target.setId(source.getDRIVERID());

	// POLICY_ID s:int Идентификатор полиса
	if (policy.getId() == source.getPOLICYID())
	    target.setPolicy(policy);
	else {
	    try {
		target.setPolicy(getById(new Long(source.getPOLICYID())));
	    } catch (NotFound e) {
		throw new DataCoruptionException(
			"Error while fetching Driver ID = '" + source.getDRIVERID()
				+ "' at Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. Policty with ID = '" + source.getPOLICYID() + "' not found");
	    }
	}

	// CLIENT_ID s:int Идентификатор клиента (обязательно)
	try {
	    target.setInsuredPerson(subjectPersonService.getById(new Long(source.getCLIENTID())));
	} catch (NotFound e) {
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
	} catch (NotFound e) {
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
	} catch (NotFound e) {
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
	DriverLicenseInfo ci = new DriverLicenseInfo();
	target.setDriverLicense(ci);
	ci.setNumber(source.getDRIVERCERTIFICATE());
	ci.setDateOfIssue(convertESBDDateToLocalDate(source.getDRIVERCERTIFICATEDATE()));

	// getClassId
	try {
	    target.setInsuraceClassType(insuranceClassTypeService.getById(source.getClassId()));
	} catch (NotFound e) {
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
	PrivilegerInfo pi = new PrivilegerInfo();
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
	GPWParticipantInfo gpwi = new GPWParticipantInfo();
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
	PensionerInfo pei = new PensionerInfo();
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
	InvalidInfo ii = new InvalidInfo();
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
	RecordOperationInfo created = new RecordOperationInfo();
	target.setCreated(created);
	created.setDate(convertESBDDateToLocalDate(source.getINPUTDATE()));
	try {
	    created.setAuthor(userService.getById(new Long(source.getCREATEDBYUSERID())));
	} catch (NotFound e) {
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
	RecordOperationInfo modified = new RecordOperationInfo();
	target.setModified(modified);
	modified.setDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()));
	if (modified.getDate() != null)
	    try {
		modified.setAuthor(userService.getById(new Long(source.getCHANGEDBYUSERID())));
	    } catch (NotFound e) {
		throw new DataCoruptionException(
			"Error while fetching Driver ID = '" + source.getDRIVERID()
				+ "' at Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. Modified User ID = '" + source.getCHANGEDBYUSERID()
				+ "' not found",
			e);
	    }

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	try {
	    target.setInsurer(insuranceCompanyService.getById(new Long(source.getSYSTEMDELIMITERID())));
	} catch (NotFound e) {
	    throw new DataCoruptionException(
		    "Error while fetching Driver ID = '" + source.getDRIVERID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Insurance company ID = '" + source.getSYSTEMDELIMITERID()
			    + "' not found",
		    e);
	}

    }

    void fillValues(PoliciesTF source, InsuredVehicleEntity target, PolicyEntity policy) {
	// POLICY_TF_ID s:int Идентификатор ТС полиса
	target.setId(source.getPOLICYTFID());

	// POLICY_ID s:int Идентификатор полиса
	if (policy.getId() == source.getPOLICYID())
	    target.setPolicy(policy);
	else {
	    try {
		target.setPolicy(getById(new Long(source.getPOLICYID())));
	    } catch (NotFound e) {
		throw new DataCoruptionException(
			"Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
				+ "' at Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. Policty with ID = '" + source.getPOLICYID() + "' not found",
			e);
	    }
	}

	// TF_ID s:int Идентификатор ТС
	try {
	    target.setVehicle(vehicleService.getById(new Long(source.getTFID())));
	} catch (NotFound e) {
	    // TODO is mandatory?
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Vehicle with ID = '" + source.getTFID() + "' not found",
		    e);
	}

	// TF_TYPE_ID s:int Идентификатор типа ТС (обязательно)
	try {
	    target.setVehicleClass(vehicleClassService.getById(source.getTFTYPEID()));
	} catch (NotFound e) {
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. VehicleType with ID = '" + source.getTFTYPEID() + "' not found",
		    e);
	}

	// TF_AGE_ID s:int Идентификатор возраста ТС (обязательно)
	try {
	    target.setVehicleAgeClass(vehicleAgeClassService.getById(source.getTFAGEID()));
	} catch (NotFound e) {
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
	VehicleCertificateInfo vci = new VehicleCertificateInfo();
	target.setCertificate(vci);

	vci.setRegistrationNumber(source.getTFNUMBER());
	vci.setCertificateNumber(source.getTFREGISTRATIONCERTIFICATE());
	vci.setDateOfIssue(convertESBDDateToCalendar(source.getGIVEDATE()));
	vci.setMajorCity(source.getBIGCITYBOOL() == 1);
	try {
	    vci.setRegistrationRegion(countryRegionService.getById(source.getREGIONID()));
	} catch (NotFound e) {
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
	RecordOperationInfo created = new RecordOperationInfo();
	target.setCreated(created);
	created.setDate(convertESBDDateToLocalDate(source.getINPUTDATE()));
	try {
	    created.setAuthor(userService.getById(new Long(source.getCREATEDBYUSERID())));
	} catch (NotFound e) {
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
	RecordOperationInfo modified = new RecordOperationInfo();
	target.setModified(modified);
	modified.setDate(convertESBDDateToLocalDate(source.getRECORDCHANGEDAT()));
	if (modified.getDate() != null)
	    try {
		modified.setAuthor(userService.getById(new Long(source.getCHANGEDBYUSERID())));
	    } catch (NotFound e) {
		throw new DataCoruptionException(
			"Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
				+ "' at Policy ID = '" + source.getPOLICYID()
				+ "' from ESBD. Modified User ID = '" + source.getCHANGEDBYUSERID()
				+ "' not found",
			e);
	    }

	// SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
	try {
	    target.setInsurer(insuranceCompanyService.getById(new Long(source.getSYSTEMDELIMITERID())));
	} catch (NotFound e) {
	    throw new DataCoruptionException(
		    "Error while fetching InsuredVehicle ID = '" + source.getPOLICYTFID()
			    + "' at Policy ID = '" + source.getPOLICYID()
			    + "' from ESBD. Insurance company ID = '" + source.getSYSTEMDELIMITERID()
			    + "' not found",
		    e);
	}
    }
}
