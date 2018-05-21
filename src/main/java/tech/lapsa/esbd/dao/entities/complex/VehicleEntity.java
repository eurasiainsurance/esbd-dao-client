package tech.lapsa.esbd.dao.entities.complex;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.lapsa.insurance.elements.SteeringWheelLocation;
import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(31)
public class VehicleEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final VehicleEntityBuilder builder() {
	return new VehicleEntityBuilder();
    }

    public static final class VehicleEntityBuilder {

	private Integer id;
	private VehicleClass vehicleClass;
	private String vinCode;
	private VehicleModelEntity vehicleModel;
	private SteeringWheelLocation steeringWheelLocation;
	private Integer engineVolume;
	private String engineNumber;
	private Integer enginePower;
	private String color;
	private LocalDate realeaseDate;

	private VehicleEntityBuilder() {
	}

	public VehicleEntityBuilder withId(final Integer id) {
	    this.id = MyObjects.requireNonNull(id, "id");
	    return this;
	}

	public VehicleEntityBuilder withVehicleClass(final VehicleClass vehicleClass) {
	    this.vehicleClass = MyObjects.requireNonNull(vehicleClass, "vehicleClass");
	    return this;
	}

	public VehicleEntityBuilder withVinCode(final String vinCode) {
	    this.vinCode = MyStrings.requireNonEmpty(vinCode, "vinCode");
	    return this;
	}

	public VehicleEntityBuilder withVehicleModel(final VehicleModelEntity vehicleModel) {
	    this.vehicleModel = MyObjects.requireNonNull(vehicleModel, "vehicleModel");
	    return this;
	}

	public VehicleEntityBuilder withSteeringWheelLocation(final SteeringWheelLocation steeringWheelLocation) {
	    this.steeringWheelLocation = MyObjects.requireNonNull(steeringWheelLocation, "steeringWheelLocation");
	    return this;
	}

	public VehicleEntityBuilder withEngineVolume(final Integer engineVolume) {
	    this.engineVolume = MyNumbers.requireNonZero(engineVolume, "engineVolume");
	    return this;
	}

	public VehicleEntityBuilder withEngineNumber(final String engineNumber) {
	    this.engineNumber = MyStrings.requireNonEmpty(engineNumber, "engineNumber");
	    return this;
	}

	public VehicleEntityBuilder withEnginePower(final Integer enginePower) {
	    this.enginePower = MyNumbers.requireNonZero(enginePower, "enginePower");
	    return this;
	}

	public VehicleEntityBuilder withEngine(final String engineNumber, final Integer engineVolume,
		final Integer enginePower) {
	    return withEngineNumber(engineNumber)
		    .withEnginePower(enginePower)
		    .withEngineVolume(engineVolume);
	}

	public VehicleEntityBuilder withColor(final String color) {
	    this.color = MyStrings.requireNonEmpty(color, "color");
	    return this;
	}

	public VehicleEntityBuilder withRealeaseDate(final LocalDate realeaseDate) {
	    this.realeaseDate = MyObjects.requireNonNull(realeaseDate, "realeaseDate");
	    return this;
	}

	public VehicleEntity build() throws IllegalArgumentException {
	    return new VehicleEntity(id,
		    vehicleClass,
		    vinCode,
		    vehicleModel,
		    steeringWheelLocation,
		    engineVolume,
		    engineNumber,
		    enginePower,
		    color,
		    realeaseDate);
	}

	public void buildTo(final Consumer<VehicleEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    // constructor

    private VehicleEntity(final Integer id,
	    final VehicleClass vehicleClass,
	    final String vinCode,
	    final VehicleModelEntity vehicleModel,
	    final SteeringWheelLocation steeringWheelLocation,
	    final Integer engineVolume,
	    final String engineNumber,
	    final Integer enginePower,
	    final String color,
	    final LocalDate realeaseDate) {
	this.id = id;
	this.vehicleClass = vehicleClass;
	this.vinCode = vinCode;
	this.vehicleModel = vehicleModel;
	this.steeringWheelLocation = steeringWheelLocation;
	this.engineVolume = engineVolume;
	this.engineNumber = engineNumber;
	this.enginePower = enginePower;
	this.color = color;
	this.realeaseDate = realeaseDate;
    }

    // id

    private final Integer id;

    public Integer getId() {
	return id;
    }

    // vehicleClass

    private final VehicleClass vehicleClass;

    public VehicleClass getVehicleClass() {
	return vehicleClass;
    }

    // vinCode

    private final String vinCode;

    public String getVinCode() {
	return vinCode;
    }

    // vehicleModel

    private final VehicleModelEntity vehicleModel;

    public VehicleModelEntity getVehicleModel() {
	return vehicleModel;
    }

    // steeringWheelLocation

    private final SteeringWheelLocation steeringWheelLocation;

    public SteeringWheelLocation getSteeringWheelLocation() {
	return steeringWheelLocation;
    }

    // engineVolume

    private final Integer engineVolume;

    public Integer getEngineVolume() {
	return engineVolume;
    }

    // engineNumber

    private final String engineNumber;

    public String getEngineNumber() {
	return engineNumber;
    }

    // enginePower

    private final Integer enginePower;

    public Integer getEnginePower() {
	return enginePower;
    }

    // color

    private final String color;

    public String getColor() {
	return color;
    }

    // realeaseDate

    private final LocalDate realeaseDate;

    public LocalDate getRealeaseDate() {
	return realeaseDate;
    }
}
