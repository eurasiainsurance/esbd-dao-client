package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyStreams;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(17)
public class PolicyEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // POLICY_ID s:int Идентификатор полиса (обязательно)
    Integer id;

    public Integer getId() {
	return id;
    }

    // GLOBAL_ID s:string Уникальный глобальный идентификатор полиса
    String number;

    public String getNumber() {
	return number;
    }

    // POLICY_NUMBER s:string Номер полиса (обязательно)
    String internalNumber;

    public String getInternalNumber() {
	return internalNumber;
    }

    // DATE_BEG s:string Дата начала действия полиса (обязательно)
    LocalDate validFrom;

    public LocalDate getValidFrom() {
	return validFrom;
    }

    // DATE_END s:string Дата окончания действия полиса (обязательно)
    LocalDate validTill;

    public LocalDate getValidTill() {
	return validTill;
    }

    // PREMIUM s:double Страховая премия (обязательно)
    Double actualPremium;

    public Double getActualPremium() {
	return actualPremium;
    }

    // CALCULATED_PREMIUM s:double Страховая премия рассчитанная системой
    Double calculatedPremium;

    public Double getCalculatedPremium() {
	return calculatedPremium;
    }

    // SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
    int _insurer;
    InsuranceCompanyEntity insurer;

    public InsuranceCompanyEntity getInsurer() {
	return insurer;
    }

    void setInsurer(final InsuranceCompanyEntity insurer) {
	this.insurer = insurer;
    }

    // CLIENT_ID s:int Идентификатор страхователя (обязательно)
    int _insurant;
    SubjectEntity insurant;

    public SubjectEntity getInsurant() {
	return insurant;
    }

    void setInsurant(final SubjectEntity insurant) {
	this.insurant = insurant;
    }

    // POLICY_DATE s:string Дата полиса
    LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // RESCINDING_DATE s:string Дата расторжения полиса
    LocalDate dateOfCancelation;

    public LocalDate getDateOfCancelation() {
	return dateOfCancelation;
    }

    // RESCINDING_REASON_ID s:int Идентификатор причины расторжения
    int _cancelationReasonType;
    CancelationReason cancelationReasonType;

    public CancelationReason getCancelationReasonType() {
	return cancelationReasonType;
    }

    // BRANCH_ID s:int Филиал (обязательно)
    int _branch;
    BranchEntity branch;

    public BranchEntity getBranch() {
	return branch;
    }

    void setBranch(final BranchEntity branch) {
	this.branch = branch;
    }

    // REWRITE_BOOL s:int Признак переоформления
    boolean reissued = false;

    public boolean isReissued() {
	return reissued;
    }

    // REWRITE_POLICY_ID s:int Ссылка на переоформляемый полис
    int _reissuedPolicy;
    PolicyEntity reissuedPolicy;

    public PolicyEntity getReissuedPolicy() {
	return reissuedPolicy;
    }

    void setReissuedPolicy(final PolicyEntity reissuedPolicy) {
	this.reissuedPolicy = reissuedPolicy;
    }

    // DESCRIPTION s:string Комментарии к полису
    String comments;

    public String getComments() {
	return comments;
    }

    // Drivers tns:ArrayOfDriver Водители (обязательно)
    private List<InsuredDriverEntity> insuredDrivers;

    public List<InsuredDriverEntity> getInsuredDrivers() {
	return MyStreams.orEmptyOf(insuredDrivers).collect(MyCollectors.unmodifiableList());
    }

    synchronized void addDriver(final InsuredDriverEntity driver) {
	if (insuredDrivers == null)
	    insuredDrivers = new ArrayList<>();
	insuredDrivers.add(driver);
    }

    // PoliciesTF tns:ArrayOfPolicies_TF Транспортные средства полиса
    // (обязательно)
    private List<InsuredVehicleEntity> insuredVehicles;

    public List<InsuredVehicleEntity> getInsuredVehicles() {
	return MyStreams.orEmptyOf(insuredVehicles).collect(MyCollectors.unmodifiableList());
    }

    synchronized void addVehicle(final InsuredVehicleEntity vehicle) {
	if (insuredVehicles == null)
	    insuredVehicles = new ArrayList<>();
	insuredVehicles.add(vehicle);
    }

    // CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего полис
    // INPUT_DATE s:string Дата\время ввода полиса в систему
    RecordOperationInfo created;

    public RecordOperationInfo getCreated() {
	return created;
    }

    // RECORD_CHANGED_AT s:string Дата\время изменения полиса
    // RECORD_CHANGED_AT_DATETIME s:string Дата\время изменения полиса
    // CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего полис
    RecordOperationInfo modified;

    public RecordOperationInfo getModified() {
	return modified;
    }

    // ScheduledPayments tns:ArrayOfSCHEDULED_PAYMENT Плановые платежи по полису
    // PAYMENT_ORDER_TYPE_ID s:int Порядок оплаты (Идентификатор)
    // PAYMENT_ORDER_TYPE s:string Порядок оплаты
    // PAYMENT_DATE s:string Дата оплаты
    // MIDDLEMAN_ID s:int Посредник (Идентификатор)
    // MIDDLEMAN_CONTRACT_NUMBER s:string Номер договора посредника
    // CLIENT_FORM_ID s:int Форма клиента (справочник CLIENT_FORMS)
}
