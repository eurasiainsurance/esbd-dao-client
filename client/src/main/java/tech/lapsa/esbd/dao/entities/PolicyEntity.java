package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.esbd.dao.entities.InsuredDriverEntity.InsuredDriverEntityBuilder;
import tech.lapsa.esbd.dao.entities.InsuredVehicleEntity.InsuredVehicleEntityBuilder;
import tech.lapsa.java.commons.function.MyCollections;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyObjects;
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
	private final List<InsuredDriverEntityBuilder> insuredDrivers = new ArrayList<>();
	private final List<InsuredVehicleEntityBuilder> insuredVehicles = new ArrayList<>();
	private RecordOperationInfo created;
	private RecordOperationInfo modified;

	public PolicyEntityBuilder withId(final Integer id) throws IllegalArgumentException {
	    this.id = id;
	    return this;
	}

	public PolicyEntityBuilder withNumber(final String number) throws IllegalArgumentException {
	    this.number = number;
	    return this;
	}

	public PolicyEntityBuilder withInternalNumber(final String internalNumber) throws IllegalArgumentException {
	    this.internalNumber = internalNumber;
	    return this;
	}

	public PolicyEntityBuilder withValidFrom(final LocalDate validFrom) throws IllegalArgumentException {
	    this.validFrom = validFrom;
	    return this;
	}

	public PolicyEntityBuilder withValidTill(final LocalDate validTill) throws IllegalArgumentException {
	    this.validTill = validTill;
	    return this;
	}

	public PolicyEntityBuilder withActualPremium(final Double actualPremium) throws IllegalArgumentException {
	    this.actualPremium = actualPremium;
	    return this;
	}

	public PolicyEntityBuilder withCalculatedPremium(final Double calculatedPremium)
		throws IllegalArgumentException {
	    this.calculatedPremium = calculatedPremium;
	    return this;
	}

	public PolicyEntityBuilder withInsurer(final InsuranceCompanyEntity insurer) throws IllegalArgumentException {
	    this.insurer = insurer;
	    return this;
	}

	public PolicyEntityBuilder withInsurant(final SubjectEntity insurant) throws IllegalArgumentException {
	    this.insurant = insurant;
	    return this;
	}

	public PolicyEntityBuilder withDateOfIssue(final LocalDate dateOfIssue) throws IllegalArgumentException {
	    this.dateOfIssue = dateOfIssue;
	    return this;
	}

	public PolicyEntityBuilder withDateOfCancelation(final LocalDate dateOfCancelation)
		throws IllegalArgumentException {
	    this.dateOfCancelation = dateOfCancelation;
	    return this;
	}

	public PolicyEntityBuilder withCancelationReasonType(final CancelationReason cancelationReasonType)
		throws IllegalArgumentException {
	    this.cancelationReasonType = cancelationReasonType;
	    return this;
	}

	public PolicyEntityBuilder withCancelationReasonType(
		final Optional<CancelationReason> optCancelationReasonType) throws IllegalArgumentException {
	    return withCancelationReasonType(
		    MyObjects.requireNonNull(optCancelationReasonType, "optCancelationReasonType").orElse(null));
	}

	public PolicyEntityBuilder withBranch(final BranchEntity branch) throws IllegalArgumentException {
	    this.branch = branch;
	    return this;
	}

	public PolicyEntityBuilder withReissuedPolicy(final PolicyEntity reissuedPolicy)
		throws IllegalArgumentException {
	    this.reissuedPolicy = reissuedPolicy;
	    return this;
	}

	public PolicyEntityBuilder withComments(final String comments) throws IllegalArgumentException {
	    this.comments = comments;
	    return this;
	}

	public PolicyEntityBuilder addDriverBuilder(final InsuredDriverEntityBuilder insuredDriver)
		throws IllegalArgumentException {
	    insuredDrivers.add(MyObjects.requireNonNull(insuredDriver, "insuredDriver"));
	    return this;
	}

	public PolicyEntityBuilder addVehicleBuilder(final InsuredVehicleEntityBuilder insuredVehicle)
		throws IllegalArgumentException {
	    insuredVehicles.add(MyObjects.requireNonNull(insuredVehicle, "insuredVehicle"));
	    return this;
	}

	public PolicyEntityBuilder withCreated(final RecordOperationInfo created) throws IllegalArgumentException {
	    this.created = created;
	    return this;
	}

	public PolicyEntityBuilder withModified(final RecordOperationInfo modified) throws IllegalArgumentException {
	    this.modified = modified;
	    return this;
	}

	public PolicyEntity build() throws IllegalArgumentException {
	    final PolicyEntity res = new PolicyEntity();
	    res.id = id;
	    res.number = number;
	    res.internalNumber = internalNumber;
	    res.validFrom = validFrom;
	    res.validTill = validTill;
	    res.actualPremium = actualPremium;
	    res.calculatedPremium = calculatedPremium;
	    res.insurer = insurer;
	    res.insurant = insurant;
	    res.dateOfIssue = dateOfIssue;
	    res.dateOfCancelation = dateOfCancelation;
	    res.cancelationReasonType = cancelationReasonType;
	    res.branch = branch;
	    res.reissuedPolicy = reissuedPolicy;
	    res.comments = comments;
	    res.insuredDrivers = MyCollections.requireNonEmpty(insuredDrivers, "insuredDrivers")
		    .stream()
		    .map(x -> x.withPolicy(res))
		    .map(InsuredDriverEntityBuilder::build)
		    .collect(MyCollectors.unmodifiableList());
	    res.insuredVehicles = MyCollections.requireNonEmpty(insuredVehicles, "insuredVehicles")
		    .stream()
		    .map(x -> x.withPolicy(res))
		    .map(InsuredVehicleEntityBuilder::build)
		    .collect(MyCollectors.unmodifiableList());
	    res.created = MyObjects.requireNonNull(created, "created");
	    res.modified = modified;
	    return res;
	}
    }

    private PolicyEntity() {
    }

    // id

    private Integer id;

    public Integer getId() {
	return id;
    }

    // number

    private String number;

    public String getNumber() {
	return number;
    }

    // internalNumber

    private String internalNumber;

    public String getInternalNumber() {
	return internalNumber;
    }

    // validFrom

    private LocalDate validFrom;

    public LocalDate getValidFrom() {
	return validFrom;
    }

    // validTill

    private LocalDate validTill;

    public LocalDate getValidTill() {
	return validTill;
    }

    // actualPremium

    private Double actualPremium;

    public Double getActualPremium() {
	return actualPremium;
    }

    // calculatedPremium

    private Double calculatedPremium;

    public Double getCalculatedPremium() {
	return calculatedPremium;
    }

    // insurer

    private InsuranceCompanyEntity insurer;

    public InsuranceCompanyEntity getInsurer() {
	return insurer;
    }

    // insurant

    private SubjectEntity insurant;

    public SubjectEntity getInsurant() {
	return insurant;
    }

    // dateOfIssue

    private LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // dateOfCancelation

    private LocalDate dateOfCancelation;

    public LocalDate getDateOfCancelation() {
	return dateOfCancelation;
    }

    // cancelationReasonType

    private CancelationReason cancelationReasonType;

    public CancelationReason getCancelationReasonType() {
	return cancelationReasonType;
    }

    // branch

    private BranchEntity branch;

    public BranchEntity getBranch() {
	return branch;
    }

    // reissuedPolicy

    private PolicyEntity reissuedPolicy;

    public boolean isReissued() {
	return MyObjects.nonNull(reissuedPolicy);
    }

    public PolicyEntity getReissuedPolicy() {
	return reissuedPolicy;
    }

    // comments

    private String comments;

    public String getComments() {
	return comments;
    }

    // insuredDrivers

    private List<InsuredDriverEntity> insuredDrivers;

    public List<InsuredDriverEntity> getInsuredDrivers() {
	return insuredDrivers;
    }

    // insuredVehicles

    private List<InsuredVehicleEntity> insuredVehicles;

    public List<InsuredVehicleEntity> getInsuredVehicles() {
	return insuredVehicles;
    }

    // created

    private RecordOperationInfo created;

    public RecordOperationInfo getCreated() {
	return created;
    }

    // modified

    private RecordOperationInfo modified;

    public boolean isModified() {
	return MyObjects.nonNull(modified);
    }

    public RecordOperationInfo getModified() {
	return modified;
    }
}
