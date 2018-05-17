package tech.lapsa.esbd.dao.entities;

import java.util.function.Consumer;

import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(13)
public class InsuredVehicleEntity extends Domain {

    private static final long serialVersionUID = 1L;

    public static InsuredVehicleEntityBuilder builder() {
	return new InsuredVehicleEntityBuilder();
    }

    public static final class InsuredVehicleEntityBuilder {

	private Integer id;
	private PolicyEntity policy;
	private VehicleEntity vehicle;
	private VehicleClass vehicleClass;
	private VehicleAgeClass vehicleAgeClass;
	private VehicleCertificateInfo certificate;
	private String vehiclePurpose;
	private int currentOdometerValue;
	private RecordOperationInfo created;
	private RecordOperationInfo modified;
	private InsuranceCompanyEntity insurer;

	private InsuredVehicleEntityBuilder() {
	}

	public InsuredVehicleEntityBuilder withId(final Integer id) throws IllegalArgumentException {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public InsuredVehicleEntityBuilder withPolicy(final PolicyEntity policy) throws IllegalArgumentException {
	    this.policy = MyObjects.requireNonNull(policy, "policy");
	    return this;
	}

	public InsuredVehicleEntityBuilder withVehicle(final VehicleEntity vehicle) throws IllegalArgumentException {
	    this.vehicle = MyObjects.requireNonNull(vehicle, "vehicle");
	    return this;
	}

	public InsuredVehicleEntityBuilder withVehicleClass(final VehicleClass vehicleClass)
		throws IllegalArgumentException {
	    this.vehicleClass = MyObjects.requireNonNull(vehicleClass, "vehicleClass");
	    return this;
	}

	public InsuredVehicleEntityBuilder withVehicleAgeClass(final VehicleAgeClass vehicleAgeClass)
		throws IllegalArgumentException {
	    this.vehicleAgeClass = MyObjects.requireNonNull(vehicleAgeClass, "vehicleAgeClass");
	    return this;
	}

	public InsuredVehicleEntityBuilder withCertificate(final VehicleCertificateInfo certificate)
		throws IllegalArgumentException {
	    this.certificate = MyObjects.requireNonNull(certificate, "certificate");
	    return this;
	}

	public InsuredVehicleEntityBuilder withVehiclePurpose(final String vehiclePurpose)
		throws IllegalArgumentException {
	    this.vehiclePurpose = vehiclePurpose;
	    return this;
	}

	public InsuredVehicleEntityBuilder withCurrentOdometerValue(final int currentOdometerValue)
		throws IllegalArgumentException {
	    this.currentOdometerValue = currentOdometerValue;
	    return this;
	}

	public InsuredVehicleEntityBuilder withCreated(final RecordOperationInfo created)
		throws IllegalArgumentException {
	    this.created = MyObjects.requireNonNull(created, "created");
	    return this;
	}

	public InsuredVehicleEntityBuilder withModified(final RecordOperationInfo modified)
		throws IllegalArgumentException {
	    this.modified = modified;
	    return this;
	}

	public InsuredVehicleEntityBuilder withInsurer(final InsuranceCompanyEntity insurer)
		throws IllegalArgumentException {
	    this.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return this;
	}

	public InsuredVehicleEntity build() throws IllegalArgumentException {
	    final InsuredVehicleEntity res = new InsuredVehicleEntity();
	    res.id = MyNumbers.requirePositive(id, "id");
	    res.policy = MyObjects.requireNonNull(policy, "policy");
	    res.vehicle = MyObjects.requireNonNull(vehicle, "vehicle");
	    res.vehicleClass = MyObjects.requireNonNull(vehicleClass, "vehicleClass");
	    res.vehicleAgeClass = MyObjects.requireNonNull(vehicleAgeClass, "vehicleAgeClass");
	    res.certificate = MyObjects.requireNonNull(certificate, "certificate");
	    res.vehiclePurpose = vehiclePurpose;
	    res.currentOdometerValue = currentOdometerValue;
	    res.created = MyObjects.requireNonNull(created, "created");
	    res.modified = modified;
	    res.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return res;
	}

	public void buildTo(final Consumer<InsuredVehicleEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    private InsuredVehicleEntity() {
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

    InsuredVehicleEntity requireNotAttachedToPolicy() throws IllegalArgumentException {
	MyObjects.requireNullMsg(policy, "%1$s already attached to %2$s", InsuredVehicleEntity.class,
		PolicyEntity.class);
	return this;
    }

    InsuredVehicleEntity attachToPolicy(final PolicyEntity res) throws IllegalArgumentException {
	requireNotAttachedToPolicy();
	policy = res;
	return this;
    }

    // vehicle

    private VehicleEntity vehicle;

    public VehicleEntity getVehicle() {
	return vehicle;
    }

    // vehicleClass

    private VehicleClass vehicleClass;

    public VehicleClass getVehicleClass() {
	return vehicleClass;
    }

    // vehicleAgeClass

    private VehicleAgeClass vehicleAgeClass;

    public VehicleAgeClass getVehicleAgeClass() {
	return vehicleAgeClass;
    }

    // certificate

    private VehicleCertificateInfo certificate;

    public VehicleCertificateInfo getCertificate() {
	return certificate;
    }

    // vehiclePurpose

    private String vehiclePurpose;

    public String getVehiclePurpose() {
	return vehiclePurpose;
    }

    // currentOdometerValue

    private int currentOdometerValue;

    public int getCurrentOdometerValue() {
	return currentOdometerValue;
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
