package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(37)
public class VehicleManufacturerEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // ID s:int Идентификатор
    Integer id;

    public Integer getId() {
	return id;
    }

    // NAME s:string Наименование марки
    String name;

    public String getName() {
	return name;
    }

    // IS_FOREIGN_BOOL s:int Признак иностранной марки
    boolean foreign;

    public boolean isForeign() {
	return foreign;
    }
}
