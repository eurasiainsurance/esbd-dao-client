package tech.lapsa.insurance.esbd.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VehicleModelEntity {

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

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
