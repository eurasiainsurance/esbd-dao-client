package tech.lapsa.esbd.dao.entities;

import java.util.function.Consumer;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(37)
public class VehicleManufacturerEntity extends Domain {

    private static final long serialVersionUID = 1L;

    public static final VehicleManufacturerEntityBuilder builder() {
	return new VehicleManufacturerEntityBuilder();
    }

    public static final class VehicleManufacturerEntityBuilder {

	private Integer id;
	private String name;
	private Boolean foreign;

	private VehicleManufacturerEntityBuilder() {
	}

	public VehicleManufacturerEntityBuilder withId(final Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public VehicleManufacturerEntityBuilder withName(final String name) {
	    this.name = MyStrings.requireNonEmpty(name, "name");
	    return this;
	}

	public VehicleManufacturerEntityBuilder withForeign(final Boolean foreign) {
	    this.foreign = MyObjects.requireNonNull(foreign, "foreign");
	    return this;
	}

	public VehicleManufacturerEntity build() throws IllegalArgumentException {
	    final VehicleManufacturerEntity res = new VehicleManufacturerEntity();
	    res.id = MyNumbers.requirePositive(id, "id");
	    res.name = MyStrings.requireNonEmpty(name, "name");
	    res.foreign = MyObjects.requireNonNull(foreign, "foreign");
	    return res;
	}

	public void buildTo(final Consumer<VehicleManufacturerEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    private VehicleManufacturerEntity() {
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

    // foreign

    private Boolean foreign;

    public Boolean isForeign() {
	return foreign;
    }
}
