package tech.lapsa.esbd.dao.entities.complex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lapsa.insurance.elements.PaymentType;
import com.lapsa.insurance.elements.PersonType;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.esbd.dao.entities.dict.BranchEntity;
import tech.lapsa.esbd.dao.entities.dict.InsuranceCompanyEntity;
import tech.lapsa.esbd.dao.entities.embeded.CancelationInfo;
import tech.lapsa.esbd.dao.entities.embeded.RecordOperationInfo;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(17)
public class PolicyEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final PolicyEntityBuilder builder() {
	return new PolicyEntityBuilder();
    }

    public static final class PolicyEntityBuilder {

	private Integer id;
	private String number;
	private String internalNumber;
	private LocalDate validFrom;
	private LocalDate validTill;
	private Double actualPremium;
	private Double calculatedPremium;
	private InsuranceCompanyEntity insurer;
	private SubjectEntity insurant;
	private PersonType insurantPersonType;
	private LocalDate dateOfIssue;

	private CancelationInfo cancelation;

	private BranchEntity branch;
	private Integer reissuedPolicyId;
	private String comments;
	private List<PolicyDriverEntity> insuredDrivers;
	private List<PolicyVehicleEntity> insuredVehicles;
	private RecordOperationInfo created;
	private RecordOperationInfo modified;

	private LocalDate dateOfPayment;
	private PaymentType paymentType;

	private InsuranceAgentEntity insuranceAgent;

	private PolicyEntityBuilder() {
	}

	public PolicyEntityBuilder withId(final Integer id) throws IllegalArgumentException {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public PolicyEntityBuilder withNumber(final String number) throws IllegalArgumentException {
	    this.number = MyStrings.requireNonEmpty(number, "number");
	    return this;
	}

	public PolicyEntityBuilder withInternalNumber(final String internalNumber) throws IllegalArgumentException {
	    this.internalNumber = MyStrings.requireNonEmpty(internalNumber, "internalNumber");
	    return this;
	}

	public PolicyEntityBuilder withValidFrom(final LocalDate validFrom) throws IllegalArgumentException {
	    this.validFrom = MyObjects.requireNonNull(validFrom, "validFrom");
	    return this;
	}

	public PolicyEntityBuilder withValidTill(final LocalDate validTill) throws IllegalArgumentException {
	    this.validTill = MyObjects.requireNonNull(validTill, "validTill");
	    return this;
	}

	public PolicyEntityBuilder withActualPremium(final Double actualPremium) throws IllegalArgumentException {
	    this.actualPremium = MyNumbers.requirePositive(actualPremium, "actualPremium");
	    return this;
	}

	public PolicyEntityBuilder withCalculatedPremium(final Double calculatedPremium)
		throws IllegalArgumentException {
	    this.calculatedPremium = MyNumbers.requirePositive(calculatedPremium, "calculatedPremium");
	    return this;
	}

	public PolicyEntityBuilder withInsurer(final InsuranceCompanyEntity insurer) throws IllegalArgumentException {
	    this.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return this;
	}

	public PolicyEntityBuilder withInsurant(final SubjectEntity insurant) throws IllegalArgumentException {
	    this.insurant = MyObjects.requireNonNull(insurant, "insurant");
	    return this;
	}

	public PolicyEntityBuilder withInsurantPersonType(final PersonType insurantPersonType)
		throws IllegalArgumentException {
	    this.insurantPersonType = MyObjects.requireNonNull(insurantPersonType, "insurantPersonType");
	    return this;
	}

	public PolicyEntityBuilder withDateOfIssue(final LocalDate dateOfIssue) throws IllegalArgumentException {
	    this.dateOfIssue = MyObjects.requireNonNull(dateOfIssue, "dateOfIssue");
	    return this;
	}

	public PolicyEntityBuilder withCancelation(final CancelationInfo cancelation)
		throws IllegalArgumentException {
	    this.cancelation = MyObjects.requireNonNull(cancelation, "cancelation");
	    return this;
	}

	public PolicyEntityBuilder withBranch(final BranchEntity branch) throws IllegalArgumentException {
	    this.branch = MyObjects.requireNonNull(branch, "branch");
	    return this;
	}

	public PolicyEntityBuilder withReissuedPolicyId(final Integer reissuedPolicyId)
		throws IllegalArgumentException {
	    this.reissuedPolicyId = MyNumbers.requirePositive(reissuedPolicyId, "reissuedPolicyId");
	    return this;
	}

	public PolicyEntityBuilder withComments(final String comments) throws IllegalArgumentException {
	    this.comments = MyStrings.requireNonEmpty(comments, "comments");
	    return this;
	}

	public PolicyEntityBuilder addDriver(final PolicyDriverEntity insuredDriver)
		throws IllegalArgumentException {
	    MyObjects.requireNonNull(insuredDriver, "insuredDriver");
	    (insuredDrivers = MyObjects.getIfNull(insuredDrivers, ArrayList::new))
		    .add(insuredDriver);
	    return this;
	}

	public PolicyEntityBuilder addVehicle(final PolicyVehicleEntity insuredVehicle)
		throws IllegalArgumentException {
	    MyObjects.requireNonNull(insuredVehicle, "insuredVehicle");
	    (insuredVehicles = MyObjects.getIfNull(insuredVehicles, ArrayList::new))
		    .add(insuredVehicle);
	    return this;
	}

	public PolicyEntityBuilder withCreated(final RecordOperationInfo created) throws IllegalArgumentException {
	    this.created = MyObjects.requireNonNull(created, "created");
	    return this;
	}

	public PolicyEntityBuilder withModified(final RecordOperationInfo modified) throws IllegalArgumentException {
	    this.modified = MyObjects.requireNonNull(modified, "modified");
	    return this;
	}

	public PolicyEntityBuilder withDateOfPayment(final LocalDate dateOfPayment) throws IllegalArgumentException {
	    this.dateOfPayment = MyObjects.requireNonNull(dateOfPayment, "dateOfPayment");
	    return this;
	}

	public PolicyEntityBuilder withPaymentType(final PaymentType paymentType) {
	    this.paymentType = MyObjects.requireNonNull(paymentType, "paymentType");
	    return this;
	}

	public PolicyEntityBuilder withInsuranceAgent(final InsuranceAgentEntity insuranceAgent) {
	    this.insuranceAgent = MyObjects.requireNonNull(insuranceAgent, "insuranceAgent");
	    return this;
	}

	public PolicyEntity build() throws IllegalArgumentException {
	    return new PolicyEntity(id,
		    number,
		    internalNumber,
		    validFrom,
		    validTill,
		    actualPremium,
		    calculatedPremium,
		    insurer,
		    insurant,
		    insurantPersonType,
		    dateOfIssue,
		    cancelation,
		    branch,
		    reissuedPolicyId,
		    comments,
		    insuredDrivers,
		    insuredVehicles,
		    created,
		    modified,
		    dateOfPayment,
		    paymentType,
		    insuranceAgent);
	}
    }

    // constructor

    private PolicyEntity(Integer id,
	    final String number,
	    final String internalNumber,
	    final LocalDate validFrom,
	    final LocalDate validTill,
	    final Double actualPremium,
	    final Double calculatedPremium,
	    final InsuranceCompanyEntity insurer,
	    final SubjectEntity insurant,
	    final PersonType insurantPersonType,
	    final LocalDate dateOfIssue,
	    final CancelationInfo cancelation,
	    final BranchEntity branch,
	    final Integer reissuedPolicyId,
	    final String comments,
	    final List<PolicyDriverEntity> insuredDrivers,
	    final List<PolicyVehicleEntity> insuredVehicles,
	    final RecordOperationInfo created,
	    final RecordOperationInfo modified,
	    final LocalDate dateOfPayment,
	    final PaymentType paymentType,
	    final InsuranceAgentEntity insuranceAgent) {
	this.id = id;
	this.number = number;
	this.internalNumber = internalNumber;
	this.validFrom = validFrom;
	this.validTill = validTill;
	this.actualPremium = actualPremium;
	this.calculatedPremium = calculatedPremium;
	this.insurer = insurer;
	this.insurant = insurant;
	this.insurantPersonType = insurantPersonType;
	this.dateOfIssue = dateOfIssue;
	this.cancelation = cancelation;
	this.branch = branch;
	this.reissuedPolicyId = reissuedPolicyId;
	this.comments = comments;
	this.insuredDrivers = MyObjects.nullOrGet(insuredDrivers, Collections::unmodifiableList);
	this.insuredVehicles = MyObjects.nullOrGet(insuredVehicles, Collections::unmodifiableList);
	this.created = created;
	this.modified = modified;
	this.dateOfPayment = dateOfPayment;
	this.paymentType = paymentType;
	this.insuranceAgent = insuranceAgent;
    }

    // id

    private final Integer id;

    public Integer getId() {
	return id;
    }

    // number

    private final String number;

    public String getNumber() {
	return number;
    }

    // internalNumber

    private final String internalNumber;

    public String getInternalNumber() {
	return internalNumber;
    }

    // validFrom

    private final LocalDate validFrom;

    public LocalDate getValidFrom() {
	return validFrom;
    }

    // validTill

    private final LocalDate validTill;

    public LocalDate getValidTill() {
	return validTill;
    }

    // actualPremium

    private final Double actualPremium;

    public Double getActualPremium() {
	return actualPremium;
    }

    // calculatedPremium

    private final Double calculatedPremium;

    public Double getCalculatedPremium() {
	return calculatedPremium;
    }

    // insurer

    private final InsuranceCompanyEntity insurer;

    public InsuranceCompanyEntity getInsurer() {
	return insurer;
    }

    // insurant

    private final SubjectEntity insurant;

    public SubjectEntity getInsurant() {
	return insurant;
    }

    // insurantType

    private final PersonType insurantPersonType;

    public PersonType getInsurantPersonType() {
	return insurantPersonType;
    }

    // dateOfIssue

    private final LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // cancelation

    private final CancelationInfo cancelation;

    public CancelationInfo getCancelation() {
	return cancelation;
    }

    public boolean isCanceled() {
	return cancelation != null;
    }

    // branch

    private final BranchEntity branch;

    public BranchEntity getBranch() {
	return branch;
    }

    // reissuedPolicy

    private final Integer reissuedPolicyId;

    public boolean isReissued() {
	return MyObjects.nonNull(reissuedPolicyId);
    }

    public Integer getReissuedPolicyId() {
	return reissuedPolicyId;
    }

    // comments

    private final String comments;

    public String getComments() {
	return comments;
    }

    // insuredDrivers

    private final List<PolicyDriverEntity> insuredDrivers;

    public List<PolicyDriverEntity> getInsuredDrivers() {
	return insuredDrivers;
    }

    // insuredVehicles

    private final List<PolicyVehicleEntity> insuredVehicles;

    public List<PolicyVehicleEntity> getInsuredVehicles() {
	return insuredVehicles;
    }

    // created

    private final RecordOperationInfo created;

    public RecordOperationInfo getCreated() {
	return created;
    }

    // modified

    private final RecordOperationInfo modified;

    public boolean isModified() {
	return MyObjects.nonNull(modified);
    }

    public RecordOperationInfo getModified() {
	return modified;
    }

    // dateOfPayment

    private final LocalDate dateOfPayment;

    public LocalDate getDateOfPayment() {
	return dateOfPayment;
    }

    public boolean isPaid() {
	return MyObjects.nonNull(dateOfPayment);
    }

    // paymentType

    private final PaymentType paymentType;

    public PaymentType getPaymentType() {
	return paymentType;
    }

    // insuranceAgent

    private final InsuranceAgentEntity insuranceAgent;

    public InsuranceAgentEntity getInsuranceAgent() {
	return insuranceAgent;
    }
}
