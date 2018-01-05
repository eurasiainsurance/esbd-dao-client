package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(41)
public class VehicleModelEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // ID s:int Идентификатор модели
    Integer id;

    public Integer getId() {
	return id;
    }

    // NAME s:string Наименование модели
    String name;

    public String getName() {
	return name;
    }

    // VOITURE_MARK_ID s:int Идентификатор марки ТС
    int _manufacturer;
    VehicleManufacturerEntity manufacturer;

    public VehicleManufacturerEntity getManufacturer() {
	return manufacturer;
    }

    void setManufacturer(VehicleManufacturerEntity manufacturer) {
	this.manufacturer = manufacturer;
    }
}
