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

	public InsuredVehicleEntityBuilder withId(Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public InsuredVehicleEntityBuilder withPolicy(PolicyEntity policy) {
	    this.policy = MyObjects.requireNonNull(policy, "policy");
	    return this;
	}

	public InsuredVehicleEntityBuilder withVehicle(VehicleEntity vehicle) {
	    this.vehicle = MyObjects.requireNonNull(vehicle, "vehicle");
	    return this;
	}

	public InsuredVehicleEntityBuilder withVehicleClass(VehicleClass vehicleClass) {
	    this.vehicleClass = MyObjects.requireNonNull(vehicleClass, "vehicleClass");
	    return this;
	}

	public InsuredVehicleEntityBuilder withVehicleAgeClass(VehicleAgeClass vehicleAgeClass) {
	    this.vehicleAgeClass = MyObjects.requireNonNull(vehicleAgeClass, "vehicleAgeClass");
	    return this;
	}

	public InsuredVehicleEntityBuilder withCertificate(VehicleCertificateInfo certificate) {
	    this.certificate = MyObjects.requireNonNull(certificate, "certificate");
	    return this;
	}

	public InsuredVehicleEntityBuilder withVehiclePurpose(String vehiclePurpose) {
	    this.vehiclePurpose = vehiclePurpose;
	    return this;
	}

	public InsuredVehicleEntityBuilder withCurrentOdometerValue(int currentOdometerValue) {
	    this.currentOdometerValue = currentOdometerValue;
	    return this;
	}

	public InsuredVehicleEntityBuilder withCreated(RecordOperationInfo created) {
	    this.created = MyObjects.requireNonNull(created, "created");
	    return this;
	}

	public InsuredVehicleEntityBuilder withModified(RecordOperationInfo modified) {
	    this.modified = modified;
	    return this;
	}

	public InsuredVehicleEntityBuilder withInsurer(InsuranceCompanyEntity insurer) {
	    this.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return this;
	}

	public InsuredVehicleEntity build() {
	    return new InsuredVehicleEntity(id,
		    policy,
		    vehicle,
		    vehicleClass,
		    vehicleAgeClass,
		    certificate,
		    vehiclePurpose,
		    currentOdometerValue,
		    created,
		    modified,
		    insurer);
	}

	public void buildTo(final Consumer<InsuredVehicleEntity> consumer) {
	    consumer.accept(build());
	}
    }

    private InsuredVehicleEntity(Integer id,
	    PolicyEntity policy,
	    VehicleEntity vehicle,
	    VehicleClass vehicleClass,
	    VehicleAgeClass vehicleAgeClass,
	    VehicleCertificateInfo certificate,
	    String vehiclePurpose,
	    int currentOdometerValue,
	    RecordOperationInfo created,
	    RecordOperationInfo modified,
	    InsuranceCompanyEntity insurer) {
	this.id = MyNumbers.requirePositive(id, "id");
	this.policy = MyObjects.requireNonNull(policy, "policy");
	this.vehicle = MyObjects.requireNonNull(vehicle, "vehicle");
	this.vehicleClass = MyObjects.requireNonNull(vehicleClass, "vehicleClass");
	this.vehicleAgeClass = MyObjects.requireNonNull(vehicleAgeClass, "vehicleAgeClass");
	this.certificate = MyObjects.requireNonNull(certificate, "certificate");
	this.vehiclePurpose = vehiclePurpose;
	this.currentOdometerValue = currentOdometerValue;
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

    // vehicle

    private final VehicleEntity vehicle;

    public VehicleEntity getVehicle() {
	return vehicle;
    }

    // vehicleClass

    private final VehicleClass vehicleClass;

    public VehicleClass getVehicleClass() {
	return vehicleClass;
    }

    // vehicleAgeClass

    private final VehicleAgeClass vehicleAgeClass;

    public VehicleAgeClass getVehicleAgeClass() {
	return vehicleAgeClass;
    }

    // certificate

    private final VehicleCertificateInfo certificate;

    public VehicleCertificateInfo getCertificate() {
	return certificate;
    }

    // vehiclePurpose

    private final String vehiclePurpose;

    public String getVehiclePurpose() {
	return vehiclePurpose;
    }

    // currentOdometerValue

    private final int currentOdometerValue;

    public int getCurrentOdometerValue() {
	return currentOdometerValue;
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
