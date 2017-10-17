package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.Sex;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.SexServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.SexMapping;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class SexServiceEJB implements SexServiceDAO {

    @Override
    public Sex getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	Sex result = SexMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(Sex.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
