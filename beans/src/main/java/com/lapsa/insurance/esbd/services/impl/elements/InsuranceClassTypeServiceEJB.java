package com.lapsa.insurance.esbd.services.impl.elements;

import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.ws.soap.SOAPFaultException;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.esbd.domain.entities.general.SubjectPersonEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.InsuranceClassTypeServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.InsuranceClassTypeMapping;
import com.lapsa.insurance.esbd.services.impl.entities.AbstractESBDServiceWS;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;

@Stateless
public class InsuranceClassTypeServiceEJB extends AbstractESBDServiceWS implements InsuranceClassTypeServiceDAO {

    @Inject
    private ESBDConnectionPool pool;

    @Override
    public InsuranceClassType getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (ESBDConnection con = pool.getConnection()) {
	    String classCode = con.getClassText(new Long(id).intValue());
	    if (classCode == null || classCode.trim().equals(""))
		throw new NotFound(InsuranceClassType.class.getSimpleName() + " not found with ID = '" + id + "'");
	    return getByCode(classCode);
	}
    }

    @Override
    public InsuranceClassType getByCode(String code) throws NotFound {
	InsuranceClassType result = InsuranceClassTypeMapping.getInstance().forId(code);
	if (result == null)
	    throw new NotFound(InsuranceClassType.class.getSimpleName() + " not found with CODE = '" + code + "'");
	return result;
    }

    @Override
    public InsuranceClassType getForSubject(SubjectPersonEntity individual) throws NotFound {
	LocalDate today = LocalDate.now();
	return getForSubject(individual, today);
    }

    @Override
    public InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson, LocalDate date) throws NotFound {
	MyObjects.requireNonNull(subjectPerson, "subjectPerson");
	MyObjects.requireNonNull(date, "date");
	try (ESBDConnection con = pool.getConnection()) {
	    String esbdDate = convertLocalDateToESBDDate(date);
	    try {
		int aClassID = con.getClassId(new Long(subjectPerson.getId()).intValue(), esbdDate, 0);
		if (aClassID == 0)
		    throw new NotFound("WS-call getClassId returned zero value for CLIENT_ID = " + subjectPerson.getId()
			    + " and date = " + esbdDate);
		return getById(aClassID);
	    } catch (SOAPFaultException e) {
		throw new NotFound("WS-call getClassId returned exception for CLIENT_ID = " + subjectPerson.getId()
			+ " and date = " + esbdDate, e);
	    }
	}
    }

    @Override
    public InsuranceClassType getDefault() {
	return InsuranceClassType.CLASS_3;
    }
}
