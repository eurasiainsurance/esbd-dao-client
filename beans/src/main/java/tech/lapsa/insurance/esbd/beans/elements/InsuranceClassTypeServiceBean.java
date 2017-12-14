package tech.lapsa.insurance.esbd.beans.elements;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.xml.ws.soap.SOAPFaultException;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.ESBDDates;
import tech.lapsa.insurance.esbd.beans.elements.mapping.InsuranceClassTypeMapping;
import tech.lapsa.insurance.esbd.elements.InsuranceClassTypeService.InsuranceClassTypeServiceLocal;
import tech.lapsa.insurance.esbd.elements.InsuranceClassTypeService.InsuranceClassTypeServiceRemote;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class InsuranceClassTypeServiceBean implements InsuranceClassTypeServiceLocal, InsuranceClassTypeServiceRemote {

    @EJB
    private ConnectionPool pool;

    @Override
    public InsuranceClassType getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	try (Connection con = pool.getConnection()) {
	    final String classCode = con.getClassText(id);
	    if (classCode == null || classCode.trim().equals(""))
		throw new NotFound(InsuranceClassType.class.getSimpleName() + " not found with ID = '" + id + "'");
	    return getByCode(classCode);
	}
    }

    @Override
    public InsuranceClassType getByCode(final String code) throws IllegalArgument, NotFound {
	MyStrings.requireNonEmpty(IllegalArgument::new, code, "code");
	final InsuranceClassType result = InsuranceClassTypeMapping.getInstance().forId(code);
	if (result == null)
	    throw new NotFound(InsuranceClassType.class.getSimpleName() + " not found with CODE = '" + code + "'");
	return result;
    }

    @Override
    public InsuranceClassType getForSubject(final SubjectPersonEntity individual) throws IllegalArgument, NotFound {
	MyObjects.requireNonNull(IllegalArgument::new, individual, "individual");
	final LocalDate today = LocalDate.now();
	return getForSubject(individual, today);
    }

    @Override
    public InsuranceClassType getForSubject(final SubjectPersonEntity subjectPerson, final LocalDate date)
	    throws NotFound, IllegalArgument {
	MyObjects.requireNonNull(IllegalArgument::new, subjectPerson, "subjectPerson");
	MyObjects.requireNonNull(IllegalArgument::new, date, "date");
	try (Connection con = pool.getConnection()) {
	    final String esbdDate = ESBDDates.convertLocalDateToESBDDate(date);
	    try {
		final int aClassID = con.getClassId(new Long(subjectPerson.getId()).intValue(), esbdDate, 0);
		if (aClassID == 0)
		    throw new NotFound("WS-call getClassId returned zero value for CLIENT_ID = " + subjectPerson.getId()
			    + " and date = " + esbdDate);
		try {
		    return getById(aClassID);
		} catch (IllegalArgument e) {
		    // it should not happens
		    throw new EJBException(e.getMessage());
		}
	    } catch (final SOAPFaultException e) {
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
