package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.VehicleAgeClassServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.VehicleAgeClassMapping;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class VehicleAgeClassServiceEJB implements VehicleAgeClassServiceDAO {

    @Override
    public VehicleAgeClass getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	VehicleAgeClass result = VehicleAgeClassMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(VehicleAgeClass.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
