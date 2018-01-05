package tech.lapsa.esbd.dao.entities;

import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(13)
public class InsuredVehicleEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // POLICY_TF_ID s:int Идентификатор ТС полиса
    Integer id;

    public Integer getId() {
	return id;
    }

    // POLICY_ID s:int Идентификатор полиса
    int policyId;
    PolicyEntity policy;

    public PolicyEntity getPolicy() {
	return policy;
    }

    void setPolicy(PolicyEntity policy) {
	this.policy = policy;
    }

    // TF_ID s:int Идентификатор ТС
    int vehicleId;
    VehicleEntity vehicle;

    public VehicleEntity getVehicle() {
	return vehicle;
    }

    void setVehicle(VehicleEntity vehicle) {
	this.vehicle = vehicle;
    }

    // TF_TYPE_ID s:int Идентификатор типа ТС (обязательно)
    int vehicleClassId;
    VehicleClass vehicleClass;

    public VehicleClass getVehicleClass() {
	return vehicleClass;
    }

    void setVehicleClass(VehicleClass vehicleClass) {
	this.vehicleClass = vehicleClass;
    }

    // TF_AGE_ID s:int Идентификатор возраста ТС (обязательно)
    int vehicleAgeClassId;
    VehicleAgeClass vehicleAgeClass;

    public VehicleAgeClass getVehicleAgeClass() {
	return vehicleAgeClass;
    }

    void setVehicleAgeClass(VehicleAgeClass vehicleAgeClass) {
	this.vehicleAgeClass = vehicleAgeClass;
    }

    // TF_NUMBER s:string Гос. номер ТС
    // TF_REGISTRATION_CERTIFICATE s:string Номер тех. паспорта
    // GIVE_DATE s:string Дата выдачи тех. паспорта
    // REGION_ID s:int Идентификатор региона регистрации ТС (обязательно)
    // BIG_CITY_BOOL s:int Признак города областного значения (обязательно)
    int certificateId;
    VehicleCertificateInfo certificate;

    public VehicleCertificateInfo getCertificate() {
	return certificate;
    }

    void setCertificate(VehicleCertificateInfo certificate) {
	this.certificate = certificate;
    }

    // PURPOSE s:string Цель использования ТС
    String vehiclePurpose;

    public String getVehiclePurpose() {
	return vehiclePurpose;
    }

    // ODOMETER s:int Показания одометра
    int currentOdometerValue;

    public int getCurrentOdometerValue() {
	return currentOdometerValue;
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
    int insurerId;
    InsuranceCompanyEntity insurer;

    public InsuranceCompanyEntity getInsurer() {
	return insurer;
    }

    void setInsurer(InsuranceCompanyEntity insurer) {
	this.insurer = insurer;
    }
}
