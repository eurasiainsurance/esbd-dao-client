package tech.lapsa.esbd.dao.elements.nondict;

import java.time.LocalDate;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.domain.complex.SubjectPersonEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface InsuranceClassTypeResolverService {

    public static final String BEAN_NAME = "InsuranceClassTypeResolverServiceBean";

    @Local
    public interface InsuranceClassTypeResolverServiceLocal extends InsuranceClassTypeResolverService {
    }

    @Remote
    public interface InsuranceClassTypeResolverServiceRemote extends InsuranceClassTypeResolverService {
    }

    InsuranceClassType getDefault();

    InsuranceClassType resolveForSubject(SubjectPersonEntity subjectPerson, LocalDate date)
	    throws IllegalArgument, NotFound;

    InsuranceClassType resolveForSubject(SubjectPersonEntity subjectPerson) throws IllegalArgument, NotFound;

}
