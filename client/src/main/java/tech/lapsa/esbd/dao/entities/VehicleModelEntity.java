package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(41)
public class VehicleModelEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // ID s:int Идентификатор модели
    private Integer id;

    // NAME s:string Наименование модели
    private String name;

    // VOITURE_MARK_ID s:int Идентификатор марки ТС
    private VehicleManufacturerEntity manufacturer;

    // GENERATED

    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public VehicleManufacturerEntity getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(final VehicleManufacturerEntity manufacturer) {
	this.manufacturer = manufacturer;
    }
}
