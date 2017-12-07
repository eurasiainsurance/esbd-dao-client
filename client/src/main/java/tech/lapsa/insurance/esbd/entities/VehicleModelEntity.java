package tech.lapsa.insurance.esbd.entities;

import tech.lapsa.patterns.domain.Pojo;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(41)
public class VehicleModelEntity extends Pojo {

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

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public VehicleManufacturerEntity getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(VehicleManufacturerEntity manufacturer) {
	this.manufacturer = manufacturer;
    }
}
