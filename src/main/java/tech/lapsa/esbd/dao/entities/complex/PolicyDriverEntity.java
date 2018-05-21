package tech.lapsa.esbd.dao.entities.complex;

import java.util.function.Consumer;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.esbd.dao.entities.dict.InsuranceCompanyEntity;
import tech.lapsa.esbd.dao.entities.embeded.DriverLicenseInfo;
import tech.lapsa.esbd.dao.entities.embeded.GPWParticipantInfo;
import tech.lapsa.esbd.dao.entities.embeded.HandicappedInfo;
import tech.lapsa.esbd.dao.entities.embeded.PensionerInfo;
import tech.lapsa.esbd.dao.entities.embeded.PrivilegerInfo;
import tech.lapsa.esbd.dao.entities.embeded.RecordOperationInfo;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodeMultiplier;

@HashCodeMultiplier(11)
public class PolicyDriverEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final PolicyDriverEntityBuilder builder() {
	return new PolicyDriverEntityBuilder();
    }

    public static final class PolicyDriverEntityBuilder {

	private Integer id;
	private SubjectPersonEntity insuredPerson;
	private MaritalStatus maritalStatus;
	private InsuredAgeAndExpirienceClass insuredAgeExpirienceClass;
	private Integer drivingExpirience;
	private DriverLicenseInfo driverLicense;
	private InsuranceClassType insuraceClassType;
	private PrivilegerInfo privilegerInfo;
	private GPWParticipantInfo gpwParticipantInfo;
	private PensionerInfo pensionerInfo;
	private HandicappedInfo handicappedInfo;
	private RecordOperationInfo created;
	private RecordOperationInfo modified;
	private InsuranceCompanyEntity insurer;

	private PolicyDriverEntityBuilder() {
	}

	public PolicyDriverEntityBuilder withId(final Integer id) throws IllegalArgumentException {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public PolicyDriverEntityBuilder withInsuredPerson(final SubjectPersonEntity insuredPerson)
		throws IllegalArgumentException {
	    this.insuredPerson = MyObjects.requireNonNull(insuredPerson, "insuredPerson");
	    return this;
	}

	public PolicyDriverEntityBuilder withMaritalStatus(final MaritalStatus maritalStatus)
		throws IllegalArgumentException {
	    this.maritalStatus = MyObjects.requireNonNull(maritalStatus, "maritalStatus");
	    return this;
	}

	public PolicyDriverEntityBuilder withInsuredAgeExpirienceClass(
		final InsuredAgeAndExpirienceClass insuredAgeExpirienceClass) throws IllegalArgumentException {
	    this.insuredAgeExpirienceClass = MyObjects.requireNonNull(insuredAgeExpirienceClass,
		    "insuredAgeExpirienceClass");
	    return this;
	}

	public PolicyDriverEntityBuilder withDrivingExpirience(final Integer drivingExpirience)
		throws IllegalArgumentException {
	    this.drivingExpirience = MyObjects.requireNonNull(drivingExpirience, "drivingExpirience");
	    return this;
	}

	public PolicyDriverEntityBuilder withDriverLicense(final DriverLicenseInfo driverLicense)
		throws IllegalArgumentException {
	    this.driverLicense = MyObjects.requireNonNull(driverLicense, "driverLicense");
	    return this;
	}

	public PolicyDriverEntityBuilder withInsuraceClassType(final InsuranceClassType insuraceClassType)
		throws IllegalArgumentException {
	    this.insuraceClassType = MyObjects.requireNonNull(insuraceClassType, "insuraceClassType");
	    return this;
	}

	public PolicyDriverEntityBuilder withPrivilegerInfo(final PrivilegerInfo privilegerInfo)
		throws IllegalArgumentException {
	    this.privilegerInfo = MyObjects.requireNonNull(privilegerInfo, "privilegerInfo");
	    return this;
	}

	public PolicyDriverEntityBuilder withGpwParticipantInfo(final GPWParticipantInfo gpwParticipantInfo)
		throws IllegalArgumentException {
	    this.gpwParticipantInfo = MyObjects.requireNonNull(gpwParticipantInfo, "gpwParticipantInfo");
	    return this;
	}

	public PolicyDriverEntityBuilder withPensionerInfo(final PensionerInfo pensionerInfo)
		throws IllegalArgumentException {
	    this.pensionerInfo = MyObjects.requireNonNull(pensionerInfo, "pensionerInfo");
	    return this;
	}

	public PolicyDriverEntityBuilder withHandicappedInfo(final HandicappedInfo handicappedInfo)
		throws IllegalArgumentException {
	    this.handicappedInfo = MyObjects.requireNonNull(handicappedInfo, "handicappedInfo");
	    return this;
	}

	public PolicyDriverEntityBuilder withCreated(final RecordOperationInfo created)
		throws IllegalArgumentException {
	    this.created = MyObjects.requireNonNull(created, "created");
	    return this;
	}

	public PolicyDriverEntityBuilder withModified(final RecordOperationInfo modified)
		throws IllegalArgumentException {
	    this.modified = MyObjects.requireNonNull(modified, "modified");
	    return this;
	}

	public PolicyDriverEntityBuilder withInsurer(final InsuranceCompanyEntity insurer)
		throws IllegalArgumentException {
	    this.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return this;
	}

	public PolicyDriverEntity build() throws IllegalArgumentException {
	    return new PolicyDriverEntity(id,
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

	public void buildTo(final Consumer<PolicyDriverEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    // constructor

    private PolicyDriverEntity(final Integer id,
	    final SubjectPersonEntity insuredPerson,
	    final MaritalStatus maritalStatus,
	    final InsuredAgeAndExpirienceClass insuredAgeExpirienceClass,
	    final Integer drivingExpirience,
	    final DriverLicenseInfo driverLicense,
	    final InsuranceClassType insuraceClassType,
	    final PrivilegerInfo privilegerInfo,
	    final GPWParticipantInfo gpwParticipantInfo,
	    final PensionerInfo pensionerInfo,
	    final HandicappedInfo handicappedInfo,
	    final RecordOperationInfo created,
	    final RecordOperationInfo modified,
	    final InsuranceCompanyEntity insurer) {
	this.id = id;
	this.insuredPerson = insuredPerson;
	this.maritalStatus = maritalStatus;
	this.insuredAgeExpirienceClass = insuredAgeExpirienceClass;
	this.drivingExpirience = drivingExpirience;
	this.driverLicense = driverLicense;
	this.insuraceClassType = insuraceClassType;
	this.privilegerInfo = privilegerInfo;
	this.gpwParticipantInfo = gpwParticipantInfo;
	this.pensionerInfo = pensionerInfo;
	this.handicappedInfo = handicappedInfo;
	this.created = created;
	this.modified = modified;
	this.insurer = insurer;
    }

    // id

    private final Integer id;

    public Integer getId() {
	return id;
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

    private final Integer drivingExpirience;

    public Integer getDrivingExpirience() {
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
