package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.CountryServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.CountryMapping;
import com.lapsa.international.country.Country;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class CountryServiceEJB implements CountryServiceDAO {

    @Override
    public Country getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	Country result = CountryMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(Country.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
