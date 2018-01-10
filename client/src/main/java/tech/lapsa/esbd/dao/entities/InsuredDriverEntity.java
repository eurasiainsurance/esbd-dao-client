package tech.lapsa.esbd.dao.entities;

import java.util.function.Consumer;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodeMultiplier;

@HashCodeMultiplier(11)
public class InsuredDriverEntity extends Domain {

    private static final long serialVersionUID = 1L;

    public static final InsuredDriverEntityBuilder builder() {
	return new InsuredDriverEntityBuilder();
    }

    public static final class InsuredDriverEntityBuilder {

	private Integer id;
	private PolicyEntity policy;
	private SubjectPersonEntity insuredPerson;
	private MaritalStatus maritalStatus;
	private InsuredAgeAndExpirienceClass insuredAgeExpirienceClass;
	private int drivingExpirience;
	private DriverLicenseInfo driverLicense;
	private InsuranceClassType insuraceClassType;
	private PrivilegerInfo privilegerInfo;
	private GPWParticipantInfo gpwParticipantInfo;
	private PensionerInfo pensionerInfo;
	private HandicappedInfo handicappedInfo;
	private RecordOperationInfo created;
	private RecordOperationInfo modified;
	private InsuranceCompanyEntity insurer;

	private InsuredDriverEntityBuilder() {
	}

	public InsuredDriverEntityBuilder withId(Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public InsuredDriverEntityBuilder withPolicy(PolicyEntity policy) {
	    this.policy = MyObjects.requireNonNull(policy, "policy");
	    return this;
	}

	public InsuredDriverEntityBuilder withInsuredPerson(SubjectPersonEntity insuredPerson) {
	    this.insuredPerson = MyObjects.requireNonNull(insuredPerson, "insuredPerson");
	    return this;
	}

	public InsuredDriverEntityBuilder withMaritalStatus(MaritalStatus maritalStatus) {
	    this.maritalStatus = MyObjects.requireNonNull(maritalStatus, "maritalStatus");
	    return this;
	}

	public InsuredDriverEntityBuilder withInsuredAgeExpirienceClass(
		InsuredAgeAndExpirienceClass insuredAgeExpirienceClass) {
	    this.insuredAgeExpirienceClass = MyObjects.requireNonNull(insuredAgeExpirienceClass,
		    "insuredAgeExpirienceClass");
	    return this;
	}

	public InsuredDriverEntityBuilder withDrivingExpirience(int drivingExpirience) {
	    this.drivingExpirience = drivingExpirience;
	    return this;
	}

	public InsuredDriverEntityBuilder withDriverLicense(DriverLicenseInfo driverLicense) {
	    this.driverLicense = MyObjects.requireNonNull(driverLicense, "driverLicense");
	    return this;
	}

	public InsuredDriverEntityBuilder withInsuraceClassType(InsuranceClassType insuraceClassType) {
	    this.insuraceClassType = MyObjects.requireNonNull(insuraceClassType, "insuraceClassType");
	    return this;
	}

	public InsuredDriverEntityBuilder withPrivilegerInfo(PrivilegerInfo privilegerInfo) {
	    this.privilegerInfo = privilegerInfo;
	    return this;
	}

	public InsuredDriverEntityBuilder withGpwParticipantInfo(GPWParticipantInfo gpwParticipantInfo) {
	    this.gpwParticipantInfo = gpwParticipantInfo;
	    return this;
	}

	public InsuredDriverEntityBuilder withPensionerInfo(PensionerInfo pensionerInfo) {
	    this.pensionerInfo = pensionerInfo;
	    return this;
	}

	public InsuredDriverEntityBuilder withHandicappedInfo(HandicappedInfo handicappedInfo) {
	    this.handicappedInfo = handicappedInfo;
	    return this;
	}

	public InsuredDriverEntityBuilder withCreated(RecordOperationInfo created) {
	    this.created = MyObjects.requireNonNull(created, "created");
	    return this;
	}

	public InsuredDriverEntityBuilder withModified(RecordOperationInfo modified) {
	    this.modified = modified;
	    return this;
	}

	public InsuredDriverEntityBuilder withInsurer(InsuranceCompanyEntity insurer) {
	    this.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return this;
	}

	public InsuredDriverEntity build() {
	    return new InsuredDriverEntity(id,
		    policy,
		    insuredPerson,
		    maritalStatus,
		    insuredAgeExpirienceClass,
		    drivingExpirience,
		    driverLicense,
		    insuraceClassType,
		    privilegerInfo,
		    gpwParticipantInfo,
		    pensionerInfo,
		    handicappedInfo,
		    created,
		    modified,
		    insurer);
	}

	public void buildTo(final Consumer<InsuredDriverEntity> consumer) {
	    consumer.accept(build());
	}
    }

    private InsuredDriverEntity(final Integer id,
	    final PolicyEntity policy,
	    final SubjectPersonEntity insuredPerson,
	    final MaritalStatus maritalStatus,
	    final InsuredAgeAndExpirienceClass insuredAgeExpirienceClass,
	    final int drivingExpirience,
	    final DriverLicenseInfo driverLicense,
	    final InsuranceClassType insuraceClassType,
	    final PrivilegerInfo privilegerInfo,
	    final GPWParticipantInfo gpwParticipantInfo,
	    final PensionerInfo pensionerInfo,
	    final HandicappedInfo handicappedInfo,
	    final RecordOperationInfo created,
	    final RecordOperationInfo modified,
	    final InsuranceCompanyEntity insurer) {
	this.id = MyNumbers.requirePositive(id, "id");
	this.policy = MyObjects.requireNonNull(policy, "policy");
	this.insuredPerson = MyObjects.requireNonNull(insuredPerson, "insuredPerson");
	this.maritalStatus = MyObjects.requireNonNull(maritalStatus, "maritalStatus");
	this.insuredAgeExpirienceClass = MyObjects.requireNonNull(insuredAgeExpirienceClass,
		"insuredAgeExpirienceClass");
	this.drivingExpirience = drivingExpirience;
	this.driverLicense = MyObjects.requireNonNull(driverLicense, "driverLicense");
	this.insuraceClassType = MyObjects.requireNonNull(insuraceClassType, "insuraceClassType");
	this.privilegerInfo = privilegerInfo;
	this.gpwParticipantInfo = gpwParticipantInfo;
	this.pensionerInfo = pensionerInfo;
	this.handicappedInfo = handicappedInfo;
	this.created = MyObjects.requireNonNull(created, "created");
	this.modified = modified;
	this.insurer = MyObjects.requireNonNull(insurer, "insurer");
    }

    // id

    private final Integer id;

    public Integer getId() {
	return id;
    }

    // policy

    private final PolicyEntity policy;

    public PolicyEntity getPolicy() {
	return policy;
    }

    // insuredPerson

    private final SubjectPersonEntity insuredPerson;

    public SubjectPersonEntity getInsuredPerson() {
	return insuredPerson;
    }

    // maritalStatus

    private final MaritalStatus maritalStatus;

    public MaritalStatus getMaritalStatus() {
	return maritalStatus;
    }

    // insuredAgeExpirienceClass

    private final InsuredAgeAndExpirienceClass insuredAgeExpirienceClass;

    public InsuredAgeAndExpirienceClass getInsuredAgeExpirienceClass() {
	return insuredAgeExpirienceClass;
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

    // drivingExpirience

    private final int drivingExpirience;

    public int getDrivingExpirience() {
	return drivingExpirience;
    }

    private final DriverLicenseInfo driverLicense;

    public DriverLicenseInfo getDriverLicense() {
	return driverLicense;
    }

    // insuraceClassType

    private final InsuranceClassType insuraceClassType;

    public InsuranceClassType getInsuraceClassType() {
	return insuraceClassType;
    }

    // privilegerInfo

    private final PrivilegerInfo privilegerInfo;

    public boolean isPrivileger() {
	return MyObjects.nonNull(privilegerInfo);
    }

    public PrivilegerInfo getPrivilegerInfo() {
	return privilegerInfo;
    }

    // gpwParticipantInfo

    private final GPWParticipantInfo gpwParticipantInfo;

    public boolean isGpwParticipant() {
	return MyObjects.nonNull(gpwParticipantInfo);
    }

    public GPWParticipantInfo getGpwParticipantInfo() {
	return gpwParticipantInfo;
    }

    // pensionerInfo

    private final PensionerInfo pensionerInfo;

    public boolean isPensioner() {
	return MyObjects.nonNull(pensionerInfo);
    }

    public PensionerInfo getPensionerInfo() {
	return pensionerInfo;
    }

    // handicappedInfo

    private final HandicappedInfo handicappedInfo;

    public boolean isHandicapped() {
	return MyObjects.nonNull(handicappedInfo);
    }

    public HandicappedInfo getHandicappedInfo() {
	return handicappedInfo;
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

    // insurer

    private final InsuranceCompanyEntity insurer;

    public InsuranceCompanyEntity getInsurer() {
	return insurer;
    }
}
