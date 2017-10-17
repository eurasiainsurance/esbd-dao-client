package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.KZCityServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.KZCityMapping;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class KZCityServiceEJB implements KZCityServiceDAO {

    @Override
    public KZCity getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	KZCity result = KZCityMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(String.format("%1$s not found with ID = '%2$s'", KZCity.class.getSimpleName(), id));
	return result;
    }
}
