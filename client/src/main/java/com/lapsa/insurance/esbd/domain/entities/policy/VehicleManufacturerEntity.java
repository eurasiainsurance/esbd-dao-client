package com.lapsa.insurance.esbd.domain.entities.policy;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VehicleManufacturerEntity {

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

    // ID s:int Идентификатор
    private long id;

    // NAME s:string Наименование марки
    private String name;

    // IS_FOREIGN_BOOL s:int Признак иностранной марки
    private boolean foreign;

    // GENERATED

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public boolean isForeign() {
	return foreign;
    }

    public void setForeign(boolean foreign) {
	this.foreign = foreign;
    }

}
