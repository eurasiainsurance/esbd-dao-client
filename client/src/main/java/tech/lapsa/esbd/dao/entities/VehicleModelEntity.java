package tech.lapsa.esbd.dao.entities;

import java.util.function.Consumer;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(41)
public class VehicleModelEntity extends Domain {

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

	public VehicleModelEntityBuilder withId(Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public VehicleModelEntityBuilder withName(String name) {
	    this.name = MyStrings.requireNonEmpty(name, "name");
	    return this;
	}

	public VehicleModelEntityBuilder withManufacturer(VehicleManufacturerEntity manufacturer) {
	    this.manufacturer = MyObjects.requireNonNull(manufacturer, "manufacturer");
	    return this;
	}

	public VehicleModelEntity build() throws IllegalArgumentException {
	    final VehicleModelEntity res = new VehicleModelEntity();
	    res.id = MyNumbers.requirePositive(id, "id");
	    res.name = MyStrings.requireNonEmpty(name, "name");
	    res.manufacturer = MyObjects.requireNonNull(manufacturer, "manufacturer");
	    return res;
	}

	public void buildTo(final Consumer<VehicleModelEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    private VehicleModelEntity() {
    }

    // id

    private Integer id;

    public Integer getId() {
	return id;
    }

    // name

    private String name;

    public String getName() {
	return name;
    }

    // manufacturer

    private VehicleManufacturerEntity manufacturer;

    public VehicleManufacturerEntity getManufacturer() {
	return manufacturer;
    }
}
