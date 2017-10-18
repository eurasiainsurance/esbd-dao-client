package tech.lapsa.insurance.esbd.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntity;
import tech.lapsa.insurance.esbd.infos.DriverLicenseInfo;
import tech.lapsa.insurance.esbd.infos.GPWParticipantInfo;
import tech.lapsa.insurance.esbd.infos.InvalidInfo;
import tech.lapsa.insurance.esbd.infos.PensionerInfo;
import tech.lapsa.insurance.esbd.infos.PrivilegerInfo;
import tech.lapsa.insurance.esbd.infos.RecordOperationInfo;

public class InsuredDriverEntity {

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    // DRIVER_ID s:int Идентификатор водителя
    private Integer id;

    // POLICY_ID s:int Идентификатор полиса
    private PolicyEntity policy;

    // CLIENT_ID s:int Идентификатор клиента (обязательно)
    private SubjectPersonEntity insuredPerson;

    // HOUSEHOLD_POSITION_ID s:int Идентификатор семейного положения
    private MaritalStatus maritalStatus;

    // AGE_EXPERIENCE_ID s:int Идентификатор возраста\стажа вождения
    private InsuredAgeAndExpirienceClass insuredAgeExpirienceClass;

    // EXPERIENCE s:int Стаж вождения
    private int drivingExpirience;

    // DRIVER_CERTIFICATE s:string Номер водительского удостоверения
    // DRIVER_CERTIFICATE_DATE s:string Дата выдачи водительского удостоверения
    private DriverLicenseInfo driverLicense = new DriverLicenseInfo();

    // getClassId
    private InsuranceClassType insuraceClassType;

    // PRIVELEGER_BOOL s:int Признак приравненного лица
    // PRIVELEDGER_TYPE s:string Тип приравненного лица
    // PRIVELEDGER_CERTIFICATE s:string Удостоверение приравненного лица
    // PRIVELEDGER_CERTIFICATE_DATE s:string Дата выдачи удостоверения
    // приравненного лица
    private boolean privileger = false;
    private PrivilegerInfo privilegerInfo = new PrivilegerInfo();

    // WOW_BOOL s:int Признак участника ВОВ
    // WOW_CERTIFICATE s:string Удостоверение участника ВОВ
    // WOW_CERTIFICATE_DATE s:string Дата выдачи удостоверения участника ВОВ
    private boolean gpwParticipant = false;
    private GPWParticipantInfo gpwParticipantInfo = new GPWParticipantInfo();

    // PENSIONER_BOOL s:int Признак пенсионера
    // PENSIONER_CERTIFICATE s:string Удостоверение пенсионера
    // PENSIONER_CERTIFICATE_DATE s:string Дата выдачи удостоверения пенсионера
    private boolean pensioner = false;
    private PensionerInfo pensionerInfo = new PensionerInfo();

    // INVALID_BOOL s:int Признак инвалида
    // INVALID_CERTIFICATE s:string Удостоверение инвалида
    // INVALID_CERTIFICATE_BEG_DATE s:string Дата выдачи удостоверения инвалида
    // INVALID_CERTIFICATE_END_DATE s:string Дата завершения удостоверения
    // инвалида
    private boolean invalid = false;
    private InvalidInfo invalidInfo = new InvalidInfo();

    // CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего запись
    // INPUT_DATE s:string Дата\время ввода записи в систему
    private RecordOperationInfo created = new RecordOperationInfo();

    // RECORD_CHANGED_AT s:string Дата\время изменения записи
    // CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего запись
    private RecordOperationInfo modified = new RecordOperationInfo();

    // SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
    private InsuranceCompanyEntity insurer;

    public InsuredAgeClass getAgeClass() {
	return insuredAgeExpirienceClass.getAgeClass();
    }

    public void setAgeClass(InsuredAgeClass ageClass) {
	insuredAgeExpirienceClass = InsuredAgeAndExpirienceClass
		.forPair(insuredAgeExpirienceClass.getExpirienceClass(), ageClass);
    }

    public InsuredExpirienceClass getExpirienceClass() {
	return insuredAgeExpirienceClass.getExpirienceClass();
    }

    public void setExpirienceClass(InsuredExpirienceClass expirienceClass) {
	insuredAgeExpirienceClass = InsuredAgeAndExpirienceClass
		.forPair(expirienceClass, insuredAgeExpirienceClass.getAgeClass());
    }

    // GENERATED

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public PolicyEntity getPolicy() {
	return policy;
    }

    public void setPolicy(PolicyEntity policy) {
	this.policy = policy;
    }

    public SubjectPersonEntity getInsuredPerson() {
	return insuredPerson;
    }

    public void setInsuredPerson(SubjectPersonEntity insuredPerson) {
	this.insuredPerson = insuredPerson;
    }

    public MaritalStatus getMaritalStatus() {
	return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
	this.maritalStatus = maritalStatus;
    }

    public InsuredAgeAndExpirienceClass getInsuredAgeExpirienceClass() {
	return insuredAgeExpirienceClass;
    }

    public void setInsuredAgeExpirienceClass(InsuredAgeAndExpirienceClass insuredAgeExpirienceClass) {
	this.insuredAgeExpirienceClass = insuredAgeExpirienceClass;
    }

    public int getDrivingExpirience() {
	return drivingExpirience;
    }

    public void setDrivingExpirience(int drivingExpirience) {
	this.drivingExpirience = drivingExpirience;
    }

    public DriverLicenseInfo getDriverLicense() {
	return driverLicense;
    }

    public void setDriverLicense(DriverLicenseInfo driverLicense) {
	this.driverLicense = driverLicense;
    }

    public InsuranceClassType getInsuraceClassType() {
	return insuraceClassType;
    }

    public void setInsuraceClassType(InsuranceClassType insuraceClassType) {
	this.insuraceClassType = insuraceClassType;
    }

    public boolean isPrivileger() {
	return privileger;
    }

    public void setPrivileger(boolean privileger) {
	this.privileger = privileger;
    }

    public PrivilegerInfo getPrivilegerInfo() {
	return privilegerInfo;
    }

    public void setPrivilegerInfo(PrivilegerInfo privilegerInfo) {
	this.privilegerInfo = privilegerInfo;
    }

    public boolean isGpwParticipant() {
	return gpwParticipant;
    }

    public void setGpwParticipant(boolean gpwParticipant) {
	this.gpwParticipant = gpwParticipant;
    }

    public GPWParticipantInfo getGpwParticipantInfo() {
	return gpwParticipantInfo;
    }

    public void setGpwParticipantInfo(GPWParticipantInfo gpwParticipantInfo) {
	this.gpwParticipantInfo = gpwParticipantInfo;
    }

    public boolean isPensioner() {
	return pensioner;
    }

    public void setPensioner(boolean pensioner) {
	this.pensioner = pensioner;
    }

    public PensionerInfo getPensionerInfo() {
	return pensionerInfo;
    }

    public void setPensionerInfo(PensionerInfo pensionerInfo) {
	this.pensionerInfo = pensionerInfo;
    }

    public boolean isInvalid() {
	return invalid;
    }

    public void setInvalid(boolean invalid) {
	this.invalid = invalid;
    }

    public InvalidInfo getInvalidInfo() {
	return invalidInfo;
    }

    public void setInvalidInfo(InvalidInfo invalidInfo) {
	this.invalidInfo = invalidInfo;
    }

    public RecordOperationInfo getCreated() {
	return created;
    }

    public void setCreated(RecordOperationInfo created) {
	this.created = created;
    }

    public RecordOperationInfo getModified() {
	return modified;
    }

    public void setModified(RecordOperationInfo modified) {
	this.modified = modified;
    }

    public InsuranceCompanyEntity getInsurer() {
	return insurer;
    }

    public void setInsurer(InsuranceCompanyEntity insurer) {
	this.insurer = insurer;
    }

}
