package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.IdentityCardType;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.IdentityCardTypeServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.IdentityCardTypeMapping;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class IdentityCardTypeServiceEJB implements IdentityCardTypeServiceDAO {

    @Override
    public IdentityCardType getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	IdentityCardType result = IdentityCardTypeMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(IdentityCardType.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
