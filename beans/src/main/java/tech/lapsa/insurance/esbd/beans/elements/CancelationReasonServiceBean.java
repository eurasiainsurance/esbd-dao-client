package com.lapsa.insurance.esbd.services.impl.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.CancelationReason;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.CancelationReasonServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.CancelationReasonMapping;

import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class CancelationReasonServiceEJB implements CancelationReasonServiceDAO {
    @Override
    public CancelationReason getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	CancelationReason result = CancelationReasonMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(CancelationReason.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
