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

	public InsuredDriverEntityBuilder withId(final Integer id) throws IllegalArgumentException {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public InsuredDriverEntityBuilder withPolicy(final PolicyEntity policy) throws IllegalArgumentException {
	    this.policy = MyObjects.requireNonNull(policy, "policy");
	    return this;
	}

	public InsuredDriverEntityBuilder withInsuredPerson(final SubjectPersonEntity insuredPerson)
		throws IllegalArgumentException {
	    this.insuredPerson = MyObjects.requireNonNull(insuredPerson, "insuredPerson");
	    return this;
	}

	public InsuredDriverEntityBuilder withMaritalStatus(final MaritalStatus maritalStatus)
		throws IllegalArgumentException {
	    this.maritalStatus = MyObjects.requireNonNull(maritalStatus, "maritalStatus");
	    return this;
	}

	public InsuredDriverEntityBuilder withInsuredAgeExpirienceClass(
		final InsuredAgeAndExpirienceClass insuredAgeExpirienceClass) throws IllegalArgumentException {
	    this.insuredAgeExpirienceClass = MyObjects.requireNonNull(insuredAgeExpirienceClass,
		    "insuredAgeExpirienceClass");
	    return this;
	}

	public InsuredDriverEntityBuilder withDrivingExpirience(final int drivingExpirience)
		throws IllegalArgumentException {
	    this.drivingExpirience = drivingExpirience;
	    return this;
	}

	public InsuredDriverEntityBuilder withDriverLicense(final DriverLicenseInfo driverLicense)
		throws IllegalArgumentException {
	    this.driverLicense = MyObjects.requireNonNull(driverLicense, "driverLicense");
	    return this;
	}

	public InsuredDriverEntityBuilder withInsuraceClassType(final InsuranceClassType insuraceClassType)
		throws IllegalArgumentException {
	    this.insuraceClassType = MyObjects.requireNonNull(insuraceClassType, "insuraceClassType");
	    return this;
	}

	public InsuredDriverEntityBuilder withPrivilegerInfo(final PrivilegerInfo privilegerInfo)
		throws IllegalArgumentException {
	    this.privilegerInfo = privilegerInfo;
	    return this;
	}

	public InsuredDriverEntityBuilder withGpwParticipantInfo(final GPWParticipantInfo gpwParticipantInfo)
		throws IllegalArgumentException {
	    this.gpwParticipantInfo = gpwParticipantInfo;
	    return this;
	}

	public InsuredDriverEntityBuilder withPensionerInfo(final PensionerInfo pensionerInfo)
		throws IllegalArgumentException {
	    this.pensionerInfo = pensionerInfo;
	    return this;
	}

	public InsuredDriverEntityBuilder withHandicappedInfo(final HandicappedInfo handicappedInfo)
		throws IllegalArgumentException {
	    this.handicappedInfo = handicappedInfo;
	    return this;
	}

	public InsuredDriverEntityBuilder withCreated(final RecordOperationInfo created)
		throws IllegalArgumentException {
	    this.created = MyObjects.requireNonNull(created, "created");
	    return this;
	}

	public InsuredDriverEntityBuilder withModified(final RecordOperationInfo modified)
		throws IllegalArgumentException {
	    this.modified = modified;
	    return this;
	}

	public InsuredDriverEntityBuilder withInsurer(final InsuranceCompanyEntity insurer)
		throws IllegalArgumentException {
	    this.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return this;
	}

	public InsuredDriverEntity build() throws IllegalArgumentException {
	    final InsuredDriverEntity res = new InsuredDriverEntity();
	    res.id = MyNumbers.requirePositive(id, "id");
	    res.policy = MyObjects.requireNonNull(policy, "policy");
	    res.insuredPerson = MyObjects.requireNonNull(insuredPerson, "insuredPerson");
	    res.maritalStatus = MyObjects.requireNonNull(maritalStatus, "maritalStatus");
	    res.insuredAgeExpirienceClass = MyObjects.requireNonNull(insuredAgeExpirienceClass,
		    "insuredAgeExpirienceClass");
	    res.drivingExpirience = drivingExpirience;
	    res.driverLicense = MyObjects.requireNonNull(driverLicense, "driverLicense");
	    res.insuraceClassType = MyObjects.requireNonNull(insuraceClassType, "insuraceClassType");
	    res.privilegerInfo = privilegerInfo;
	    res.gpwParticipantInfo = gpwParticipantInfo;
	    res.pensionerInfo = pensionerInfo;
	    res.handicappedInfo = handicappedInfo;
	    res.created = MyObjects.requireNonNull(created, "created");
	    res.modified = modified;
	    res.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return res;
	}

	public void buildTo(final Consumer<InsuredDriverEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    private InsuredDriverEntity() {
    }

    // id

    private Integer id;

    public Integer getId() {
	return id;
    }

    // policy

    private PolicyEntity policy;

    public PolicyEntity getPolicy() {
	return policy;
    }

    InsuredDriverEntity requireNotAttachedToPolicy() throws IllegalArgumentException {
	MyObjects.requireNullMsg(policy, "%1$s already attached to %2$s", InsuredDriverEntity.class,
		PolicyEntity.class);
	return this;
    }

    InsuredDriverEntity attachToPolicy(final PolicyEntity res) throws IllegalArgumentException {
	requireNotAttachedToPolicy();
	policy = res;
	return this;
    }

    // insuredPerson

    private SubjectPersonEntity insuredPerson;

    public SubjectPersonEntity getInsuredPerson() {
	return insuredPerson;
    }

    // maritalStatus

    private MaritalStatus maritalStatus;

    public MaritalStatus getMaritalStatus() {
	return maritalStatus;
    }

    // insuredAgeExpirienceClass

    InsuredAgeAndExpirienceClass insuredAgeExpirienceClass;

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

    private int drivingExpirience;

    public int getDrivingExpirience() {
	return drivingExpirience;
    }

    private DriverLicenseInfo driverLicense;

    public DriverLicenseInfo getDriverLicense() {
	return driverLicense;
    }

    // insuraceClassType

    private InsuranceClassType insuraceClassType;

    public InsuranceClassType getInsuraceClassType() {
	return insuraceClassType;
    }

    // privilegerInfo

    private PrivilegerInfo privilegerInfo;

    public boolean isPrivileger() {
	return MyObjects.nonNull(privilegerInfo);
    }

    public PrivilegerInfo getPrivilegerInfo() {
	return privilegerInfo;
    }

    // gpwParticipantInfo

    private GPWParticipantInfo gpwParticipantInfo;

    public boolean isGpwParticipant() {
	return MyObjects.nonNull(gpwParticipantInfo);
    }

    public GPWParticipantInfo getGpwParticipantInfo() {
	return gpwParticipantInfo;
    }

    // pensionerInfo

    private PensionerInfo pensionerInfo;

    public boolean isPensioner() {
	return MyObjects.nonNull(pensionerInfo);
    }

    public PensionerInfo getPensionerInfo() {
	return pensionerInfo;
    }

    // handicappedInfo

    private HandicappedInfo handicappedInfo;

    public boolean isHandicapped() {
	return MyObjects.nonNull(handicappedInfo);
    }

    public HandicappedInfo getHandicappedInfo() {
	return handicappedInfo;
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

    // insurer

    private InsuranceCompanyEntity insurer;

    public InsuranceCompanyEntity getInsurer() {
	return insurer;
    }
}
