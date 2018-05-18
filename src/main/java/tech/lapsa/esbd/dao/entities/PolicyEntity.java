package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.esbd.dao.dict.PersonTypeEntity;
import tech.lapsa.java.commons.function.MyCollections;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
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
	private PersonTypeEntity insurantPersonType;
	private LocalDate dateOfIssue;

	private CancelationInfo cancelation;

	private BranchEntity branch;
	private Integer reissuedPolicyId;
	private String comments;
	private final List<InsuredDriverEntity> insuredDrivers = new ArrayList<>();
	private final List<InsuredVehicleEntity> insuredVehicles = new ArrayList<>();
	private RecordOperationInfo created;
	private RecordOperationInfo modified;

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

	public PolicyEntityBuilder withInsurantPersonType(final PersonTypeEntity insurantPersonType)
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
	    this.comments = comments;
	    return this;
	}

	public PolicyEntityBuilder addDriver(final InsuredDriverEntity insuredDriver)
		throws IllegalArgumentException {
	    insuredDrivers.add(MyObjects.requireNonNull(insuredDriver, "insuredDriver"));
	    return this;
	}

	public PolicyEntityBuilder addVehicle(final InsuredVehicleEntity insuredVehicle)
		throws IllegalArgumentException {
	    insuredVehicles.add(MyObjects.requireNonNull(insuredVehicle, "insuredVehicle"));
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

	public PolicyEntity build() throws IllegalArgumentException {
	    final PolicyEntity res = new PolicyEntity();
	    res.id = MyNumbers.requirePositive(id, "id");
	    res.number = MyObjects.requireNonNull(number, "number");
	    res.internalNumber = MyStrings.requireNonEmpty(internalNumber, "internalNumber");
	    res.validFrom = MyObjects.requireNonNull(validFrom, "validFrom");
	    res.validTill = MyObjects.requireNonNull(validTill, "validTill");
	    res.actualPremium = MyNumbers.requirePositive(actualPremium, "actualPremium");
	    res.calculatedPremium = MyNumbers.requirePositive(calculatedPremium, "calculatedPremium");
	    res.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    res.insurant = MyObjects.requireNonNull(insurant, "insurant");
	    res.insurantPersonType = MyObjects.isA(insurant, SubjectPersonEntity.class)
		    ? MyObjects.requireNonNull(insurantPersonType, "insurantPersonType")
		    : MyObjects.requireNull(insurantPersonType, "insurantPersonType");
	    res.dateOfIssue = MyObjects.requireNonNull(dateOfIssue, "dateOfIssue");
	    res.cancelation = cancelation;
	    res.branch = MyObjects.requireNonNull(branch, "branch");
	    res.reissuedPolicyId = reissuedPolicyId;
	    res.comments = comments;
	    res.insuredDrivers = Collections
		    .unmodifiableList(MyCollections.requireNonEmpty(insuredDrivers, "insuredDrivers"));
	    res.insuredVehicles = Collections
		    .unmodifiableList(MyCollections.requireNonEmpty(insuredVehicles, "insuredVehicles"));
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

    // insurantType

    private PersonTypeEntity insurantPersonType;

    public PersonTypeEntity getInsurantPersonType() {
	return insurantPersonType;
    }

    // dateOfIssue

    private LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // cancelation

    private CancelationInfo cancelation;

    public CancelationInfo getCancelation() {
	return cancelation;
    }

    public boolean isCanceled() {
	return cancelation != null;
    }

    // branch

    private BranchEntity branch;

    public BranchEntity getBranch() {
	return branch;
    }

    // reissuedPolicy

    private Integer reissuedPolicyId;

    public boolean isReissued() {
	return MyObjects.nonNull(reissuedPolicyId);
    }

    public Integer getReissuedPolicyId() {
	return reissuedPolicyId;
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
