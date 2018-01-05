package tech.lapsa.esbd.dao.entities;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodeMultiplier;

@HashCodeMultiplier(11)
public class InsuredDriverEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // DRIVER_ID s:int Идентификатор водителя
    Integer id;

    public Integer getId() {
	return id;
    }

    // POLICY_ID s:int Идентификатор полиса
    int _policy;
    PolicyEntity policy;

    public PolicyEntity getPolicy() {
	return policy;
    }

    void setPolicy(PolicyEntity policy) {
	this.policy = policy;
    }

    // CLIENT_ID s:int Идентификатор клиента (обязательно)
    int _insuredPerson;
    SubjectPersonEntity insuredPerson;

    public SubjectPersonEntity getInsuredPerson() {
	return insuredPerson;
    }

    void setInsuredPerson(SubjectPersonEntity insuredPerson) {
	this.insuredPerson = insuredPerson;
    }

    // HOUSEHOLD_POSITION_ID s:int Идентификатор семейного положения
    int _maritalStatus;
    MaritalStatus maritalStatus;

    public MaritalStatus getMaritalStatus() {
	return maritalStatus;
    }

    void setMaritalStatus(MaritalStatus maritalStatus) {
	this.maritalStatus = maritalStatus;
    }

    // AGE_EXPERIENCE_ID s:int Идентификатор возраста\стажа вождения
    int _insuredAgeExpirienceClass;
    InsuredAgeAndExpirienceClass insuredAgeExpirienceClass;

    public InsuredAgeAndExpirienceClass getInsuredAgeExpirienceClass() {
	return insuredAgeExpirienceClass;
    }

    void setInsuredAgeExpirienceClass(InsuredAgeAndExpirienceClass insuredAgeExpirienceClass) {
	this.insuredAgeExpirienceClass = insuredAgeExpirienceClass;
    }

    // EXPERIENCE s:int Стаж вождения
    int drivingExpirience;

    public int getDrivingExpirience() {
	return drivingExpirience;
    }

    // DRIVER_CERTIFICATE s:string Номер водительского удостоверения
    // DRIVER_CERTIFICATE_DATE s:string Дата выдачи водительского удостоверения
    DriverLicenseInfo driverLicense;

    public DriverLicenseInfo getDriverLicense() {
	return driverLicense;
    }

    // getClassId
    int _insuraceClassType;
    InsuranceClassType insuraceClassType;

    public InsuranceClassType getInsuraceClassType() {
	return insuraceClassType;
    }

    void setInsuraceClassType(InsuranceClassType insuraceClassType) {
	this.insuraceClassType = insuraceClassType;
    }

    // PRIVELEGER_BOOL s:int Признак приравненного лица
    boolean privileger;

    public boolean isPrivileger() {
	return privileger;
    }

    // PRIVELEDGER_TYPE s:string Тип приравненного лица
    // PRIVELEDGER_CERTIFICATE s:string Удостоверение приравненного лица
    // PRIVELEDGER_CERTIFICATE_DATE s:string Дата выдачи удостоверения
    // приравненного лица
    PrivilegerInfo privilegerInfo;

    PrivilegerInfo getPrivilegerInfo() {
	return privilegerInfo;
    }

    // WOW_BOOL s:int Признак участника ВОВ
    boolean gpwParticipant;

    public boolean isGpwParticipant() {
	return gpwParticipant;
    }

    // WOW_CERTIFICATE s:string Удостоверение участника ВОВ
    // WOW_CERTIFICATE_DATE s:string Дата выдачи удостоверения участника ВОВ
    GPWParticipantInfo gpwParticipantInfo;

    public GPWParticipantInfo getGpwParticipantInfo() {
	return gpwParticipantInfo;
    }

    // PENSIONER_BOOL s:int Признак пенсионера
    boolean pensioner;

    public boolean isPensioner() {
	return pensioner;
    }

    // PENSIONER_CERTIFICATE s:string Удостоверение пенсионера
    // PENSIONER_CERTIFICATE_DATE s:string Дата выдачи удостоверения пенсионера
    PensionerInfo pensionerInfo;

    public PensionerInfo getPensionerInfo() {
	return pensionerInfo;
    }

    // INVALID_BOOL s:int Признак инвалида
    boolean invalid;

    public boolean isInvalid() {
	return invalid;
    }

    // INVALID_CERTIFICATE s:string Удостоверение инвалида
    // INVALID_CERTIFICATE_BEG_DATE s:string Дата выдачи удостоверения инвалида
    // INVALID_CERTIFICATE_END_DATE s:string Дата завершения удостоверения
    // инвалида

    InvalidInfo invalidInfo;

    public InvalidInfo getInvalidInfo() {
	return invalidInfo;
    }

    // CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего запись
    // INPUT_DATE s:string Дата\время ввода записи в систему
    RecordOperationInfo created;

    public RecordOperationInfo getCreated() {
	return created;
    }

    // RECORD_CHANGED_AT s:string Дата\время изменения записи
    // CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего запись
    RecordOperationInfo modified;

    public RecordOperationInfo getModified() {
	return modified;
    }

    // SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
    int _insurer;
    InsuranceCompanyEntity insurer;

    public InsuranceCompanyEntity getInsurer() {
	return insurer;
    }

    void setInsurer(InsuranceCompanyEntity insurer) {
	this.insurer = insurer;
    }

    public InsuredAgeClass getAgeClass() {
	return MyObjects.isNull(insuredAgeExpirienceClass)
		? null
		: insuredAgeExpirienceClass.getAgeClass();
    }

    public InsuredExpirienceClass getExpirienceClass() {
	return MyObjects.isNull(insuredAgeExpirienceClass)
		? null
		: insuredAgeExpirienceClass.getExpirienceClass();
    }
}
