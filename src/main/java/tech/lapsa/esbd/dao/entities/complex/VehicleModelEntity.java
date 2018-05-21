package tech.lapsa.esbd.dao.entities.complex;

import java.util.function.Consumer;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(41)
public class VehicleModelEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final VehicleModelEntityBuilder builder() {
	return new VehicleModelEntityBuilder();
    }

    public static final class VehicleModelEntityBuilder {

	private Integer id;
	private String name;
	private VehicleManufacturerEntity manufacturer;

	private VehicleModelEntityBuilder() {
	}

	public VehicleModelEntityBuilder withId(final Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public VehicleModelEntityBuilder withName(final String name) {
	    this.name = MyStrings.requireNonEmpty(name, "name");
	    return this;
	}

	public VehicleModelEntityBuilder withManufacturer(final VehicleManufacturerEntity manufacturer) {
	    this.manufacturer = MyObjects.requireNonNull(manufacturer, "manufacturer");
	    return this;
	}

	public VehicleModelEntity build() throws IllegalArgumentException {
	    return new VehicleModelEntity(id,
		    name,
		    manufacturer);
	}

	public void buildTo(final Consumer<VehicleModelEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    // constructor

    private VehicleModelEntity(final Integer id,
	    final String name,
	    final VehicleManufacturerEntity manufacturer) {
	this.id = id;
	this.name = name;
	this.manufacturer = manufacturer;
    }

    // id

    private final Integer id;

    public Integer getId() {
	return id;
    }

    // name

    private final String name;

    public String getName() {
	return name;
    }

    // manufacturer

    private final VehicleManufacturerEntity manufacturer;

    public VehicleManufacturerEntity getManufacturer() {
	return manufacturer;
    }
}
