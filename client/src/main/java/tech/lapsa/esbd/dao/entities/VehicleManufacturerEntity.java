package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(37)
public class VehicleManufacturerEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // ID s:int Идентификатор
    private Integer id;

    // NAME s:string Наименование марки
    private String name;

    // IS_FOREIGN_BOOL s:int Признак иностранной марки
    private boolean foreign;

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

    public boolean isForeign() {
	return foreign;
    }

    public void setForeign(final boolean foreign) {
	this.foreign = foreign;
    }

}
