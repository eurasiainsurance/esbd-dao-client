package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.VehicleClassServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.VehicleClassMapping;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class VehicleClassServiceEJB implements VehicleClassServiceDAO {

    @Override
    public VehicleClass getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	VehicleClass result = VehicleClassMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(VehicleClass.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
