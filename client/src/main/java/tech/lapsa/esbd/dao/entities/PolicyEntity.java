package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(17)
public class PolicyEntity extends Domain {

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
	private LocalDate dateOfIssue;
	private LocalDate dateOfCancelation;
	private CancelationReason cancelationReasonType;
	private BranchEntity branch;
	private PolicyEntity reissuedPolicy;
	private String comments;
	private List<InsuredDriverEntity> insuredDrivers = new ArrayList<>();
	private List<InsuredVehicleEntity> insuredVehicles = new ArrayList<>();
	private RecordOperationInfo created;
	private RecordOperationInfo modified;

	public PolicyEntityBuilder withId(final Integer id) {
	    this.id = id;
	    return this;
	}

	public PolicyEntityBuilder withNumber(final String number) {
	    this.number = number;
	    return this;
	}

	public PolicyEntityBuilder withInternalNumber(final String internalNumber) {
	    this.internalNumber = internalNumber;
	    return this;
	}

	public PolicyEntityBuilder withValidFrom(final LocalDate validFrom) {
	    this.validFrom = validFrom;
	    return this;
	}

	public PolicyEntityBuilder withValidTill(final LocalDate validTill) {
	    this.validTill = validTill;
	    return this;
	}

	public PolicyEntityBuilder withActualPremium(final Double actualPremium) {
	    this.actualPremium = actualPremium;
	    return this;
	}

	public PolicyEntityBuilder withCalculatedPremium(final Double calculatedPremium) {
	    this.calculatedPremium = calculatedPremium;
	    return this;
	}

	public PolicyEntityBuilder withInsurer(final InsuranceCompanyEntity insurer) {
	    this.insurer = insurer;
	    return this;
	}

	public PolicyEntityBuilder withInsurant(final SubjectEntity insurant) {
	    this.insurant = insurant;
	    return this;
	}

	public PolicyEntityBuilder withDateOfIssue(final LocalDate dateOfIssue) {
	    this.dateOfIssue = dateOfIssue;
	    return this;
	}

	public PolicyEntityBuilder withDateOfCancelation(final LocalDate dateOfCancelation) {
	    this.dateOfCancelation = dateOfCancelation;
	    return this;
	}

	public PolicyEntityBuilder withCancelationReasonType(final CancelationReason cancelationReasonType) {
	    this.cancelationReasonType = cancelationReasonType;
	    return this;
	}

	public PolicyEntityBuilder withCancelationReasonType(Optional<CancelationReason> optCancelationReasonType) {
	    return withCancelationReasonType(
		    MyObjects.requireNonNull(optCancelationReasonType, "optCancelationReasonType").orElse(null));
	}

	public PolicyEntityBuilder withBranch(final BranchEntity branch) {
	    this.branch = branch;
	    return this;
	}

	public PolicyEntityBuilder withReissuedPolicy(final PolicyEntity reissuedPolicy) {
	    this.reissuedPolicy = reissuedPolicy;
	    return this;
	}

	public PolicyEntityBuilder withComments(final String comments) {
	    this.comments = comments;
	    return this;
	}

	public PolicyEntityBuilder addDriver(final InsuredDriverEntity insuredDriver) {
	    insuredDrivers.add(MyObjects.requireNonNull(insuredDriver, "insuredDriver"));
	    return this;
	}

	public PolicyEntityBuilder addVehicle(final InsuredVehicleEntity insuredVehicle) {
	    insuredVehicles.add(MyObjects.requireNonNull(insuredVehicle, "insuredVehicle"));
	    return this;
	}

	public PolicyEntityBuilder withCreated(final RecordOperationInfo created) {
	    this.created = created;
	    return this;
	}

	public PolicyEntityBuilder withModified(final RecordOperationInfo modified) {
	    this.modified = modified;
	    return this;
	}

	public PolicyEntity build() {
	    return new PolicyEntity(id,
		    number,
		    internalNumber,
		    validFrom,
		    validTill,
		    actualPremium,
		    calculatedPremium,
		    insurer,
		    insurant,
		    dateOfIssue,
		    dateOfCancelation,
		    cancelationReasonType,
		    branch,
		    reissuedPolicy,
		    comments,
		    insuredDrivers,
		    insuredVehicles,
		    created,
		    modified);
	}
    }

    private PolicyEntity(final Integer id,
	    final String number,
	    final String internalNumber,
	    final LocalDate validFrom,
	    final LocalDate validTill,
	    final Double actualPremium,
	    final Double calculatedPremium,
	    final InsuranceCompanyEntity insurer,
	    final SubjectEntity insurant,
	    final LocalDate dateOfIssue,
	    final LocalDate dateOfCancelation,
	    final CancelationReason cancelationReasonType,
	    final BranchEntity branch,
	    final PolicyEntity reissuedPolicy,
	    final String comments,
	    final List<InsuredDriverEntity> insuredDrivers,
	    final List<InsuredVehicleEntity> insuredVehicles,
	    final RecordOperationInfo created,
	    final RecordOperationInfo modified) {
	this.id = id;
	this.number = number;
	this.internalNumber = internalNumber;
	this.validFrom = validFrom;
	this.validTill = validTill;
	this.actualPremium = actualPremium;
	this.calculatedPremium = calculatedPremium;
	this.insurer = insurer;
	this.insurant = insurant;
	this.dateOfIssue = dateOfIssue;
	this.dateOfCancelation = dateOfCancelation;
	this.cancelationReasonType = cancelationReasonType;
	this.branch = branch;
	this.reissuedPolicy = reissuedPolicy;
	this.comments = comments;
	this.insuredDrivers = Collections
		.unmodifiableList(MyOptionals.of(insuredDrivers).orElseGet(Collections::emptyList));
	this.insuredVehicles = Collections
		.unmodifiableList(MyOptionals.of(insuredVehicles).orElseGet(Collections::emptyList));
	this.created = MyObjects.requireNonNull(created, "created");
	this.modified = modified;
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

    // dateOfIssue

    private final LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // dateOfCancelation

    private final LocalDate dateOfCancelation;

    public LocalDate getDateOfCancelation() {
	return dateOfCancelation;
    }

    // cancelationReasonType

    private final CancelationReason cancelationReasonType;

    public CancelationReason getCancelationReasonType() {
	return cancelationReasonType;
    }

    // branch

    private final BranchEntity branch;

    public BranchEntity getBranch() {
	return branch;
    }

    // reissuedPolicy

    private final PolicyEntity reissuedPolicy;

    public boolean isReissued() {
	return MyObjects.nonNull(reissuedPolicy);
    }

    public PolicyEntity getReissuedPolicy() {
	return reissuedPolicy;
    }

    // comments

    private final String comments;

    public String getComments() {
	return comments;
    }

    // insuredDrivers

    private final List<InsuredDriverEntity> insuredDrivers;

    public List<InsuredDriverEntity> getInsuredDrivers() {
	return insuredDrivers;
    }

    // insuredVehicles

    private final List<InsuredVehicleEntity> insuredVehicles;

    public List<InsuredVehicleEntity> getInsuredVehicles() {
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
}
