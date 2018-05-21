package tech.lapsa.esbd.dao.entities.complex;

import java.util.function.Consumer;

import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.esbd.dao.entities.dict.InsuranceCompanyEntity;
import tech.lapsa.esbd.dao.entities.embeded.RecordOperationInfo;
import tech.lapsa.esbd.dao.entities.embeded.VehicleCertificateInfo;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(13)
public class PolicyVehicleEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public static PolicyVehicleEntityBuilder builder() {
	return new PolicyVehicleEntityBuilder();
    }

    public static final class PolicyVehicleEntityBuilder {

	private Integer id;
	private VehicleEntity vehicle;
	private VehicleClass vehicleClass;
	private VehicleAgeClass vehicleAgeClass;
	private VehicleCertificateInfo certificate;
	private String vehiclePurpose;
	private Integer currentOdometerValue;
	private RecordOperationInfo created;
	private RecordOperationInfo modified;
	private InsuranceCompanyEntity insurer;

	private PolicyVehicleEntityBuilder() {
	}

	public PolicyVehicleEntityBuilder withId(final Integer id) throws IllegalArgumentException {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public PolicyVehicleEntityBuilder withVehicle(final VehicleEntity vehicle) throws IllegalArgumentException {
	    this.vehicle = MyObjects.requireNonNull(vehicle, "vehicle");
	    return this;
	}

	public PolicyVehicleEntityBuilder withVehicleClass(final VehicleClass vehicleClass)
		throws IllegalArgumentException {
	    this.vehicleClass = MyObjects.requireNonNull(vehicleClass, "vehicleClass");
	    return this;
	}

	public PolicyVehicleEntityBuilder withVehicleAgeClass(final VehicleAgeClass vehicleAgeClass)
		throws IllegalArgumentException {
	    this.vehicleAgeClass = MyObjects.requireNonNull(vehicleAgeClass, "vehicleAgeClass");
	    return this;
	}

	public PolicyVehicleEntityBuilder withCertificate(final VehicleCertificateInfo certificate)
		throws IllegalArgumentException {
	    this.certificate = MyObjects.requireNonNull(certificate, "certificate");
	    return this;
	}

	public PolicyVehicleEntityBuilder withVehiclePurpose(final String vehiclePurpose)
		throws IllegalArgumentException {
	    this.vehiclePurpose = vehiclePurpose;
	    return this;
	}

	public PolicyVehicleEntityBuilder withCurrentOdometerValue(final Integer currentOdometerValue)
		throws IllegalArgumentException {
	    this.currentOdometerValue = MyNumbers.requirePositive(currentOdometerValue, "currentOdometerValue");
	    return this;
	}

	public PolicyVehicleEntityBuilder withCreated(final RecordOperationInfo created)
		throws IllegalArgumentException {
	    this.created = MyObjects.requireNonNull(created, "created");
	    return this;
	}

	public PolicyVehicleEntityBuilder withModified(final RecordOperationInfo modified)
		throws IllegalArgumentException {
	    this.modified = MyObjects.requireNonNull(modified, "modified");
	    return this;
	}

	public PolicyVehicleEntityBuilder withInsurer(final InsuranceCompanyEntity insurer)
		throws IllegalArgumentException {
	    this.insurer = MyObjects.requireNonNull(insurer, "insurer");
	    return this;
	}

	public PolicyVehicleEntity build() throws IllegalArgumentException {
	    final PolicyVehicleEntity res = new PolicyVehicleEntity();
	    res.id = MyNumbers.requirePositive(id, "id");
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

	public void buildTo(final Consumer<PolicyVehicleEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    private PolicyVehicleEntity() {
    }

    // id

    private Integer id;

    public Integer getId() {
	return id;
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

    private Integer currentOdometerValue;

    public Integer getCurrentOdometerValue() {
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
