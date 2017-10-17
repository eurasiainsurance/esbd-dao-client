package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.InsuredAgeAndExpirienceClassServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.InsuredAgeAndExpirienceClassMapping;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class InsuredAgeAndExpirienceClassServiceEJB
	implements InsuredAgeAndExpirienceClassServiceDAO {

    @Override
    public InsuredAgeAndExpirienceClass getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	InsuredAgeAndExpirienceClass result = InsuredAgeAndExpirienceClassMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(
		    InsuredAgeAndExpirienceClass.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
