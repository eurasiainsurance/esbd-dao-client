package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.MaritalStatus;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.MaritalStatusServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.MaritalStatusMapping;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class MaritalStatusServiceEJB implements MaritalStatusServiceDAO {

    @Override
    public MaritalStatus getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	MaritalStatus result = MaritalStatusMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(MaritalStatus.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
