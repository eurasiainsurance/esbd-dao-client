package tech.lapsa.insurance.esbd.beans.elements;

import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.ws.soap.SOAPFaultException;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.ESBDDates;
import tech.lapsa.insurance.esbd.beans.elements.mapping.InsuranceClassTypeMapping;
import tech.lapsa.insurance.esbd.elements.InsuranceClassTypeService;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;

@Stateless
public class InsuranceClassTypeServiceBean implements InsuranceClassTypeService {

    @Inject
    private ConnectionPool pool;

    @Override
    public InsuranceClassType getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (Connection con = pool.getConnection()) {
	    String classCode = con.getClassText(id);
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
	try (Connection con = pool.getConnection()) {
	    String esbdDate = ESBDDates.convertLocalDateToESBDDate(date);
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
