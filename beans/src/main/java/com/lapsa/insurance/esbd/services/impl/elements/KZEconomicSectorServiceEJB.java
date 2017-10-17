package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.KZEconomicSectorServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.KZEconomicSectorMapping;
import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class KZEconomicSectorServiceEJB implements KZEconomicSectorServiceDAO {

    @Override
    public KZEconomicSector getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	KZEconomicSector result = KZEconomicSectorMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(
		    String.format("%1$s not found with ID = '%2$s'", KZEconomicSector.class.getSimpleName(), id));
	return result;
    }
}
