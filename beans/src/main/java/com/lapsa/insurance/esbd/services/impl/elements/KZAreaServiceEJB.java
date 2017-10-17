package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.KZAreaServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.KZAreaMapping;
import com.lapsa.kz.country.KZArea;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class KZAreaServiceEJB implements KZAreaServiceDAO {

    @Override
    public KZArea getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	KZArea result = KZAreaMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(String.format("%1$s not found with ID = '%2$s'", KZArea.class.getSimpleName(), id));
	return result;
    }
}
