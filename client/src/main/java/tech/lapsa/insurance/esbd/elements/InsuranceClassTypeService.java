package tech.lapsa.insurance.esbd.elements;

import java.time.LocalDate;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface InsuranceClassTypeService extends ElementsService<InsuranceClassType, Integer> {

    @Local
    public interface InsuranceClassTypeServiceLocal extends InsuranceClassTypeService {
    }

    @Remote
    public interface InsuranceClassTypeServiceRemote extends InsuranceClassTypeService {
    }

    InsuranceClassType getDefault();

    InsuranceClassType getByCode(String code) throws IllegalArgument, NotFound;

    InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson, LocalDate date)
	    throws IllegalArgument, NotFound;

    InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson) throws IllegalArgument, NotFound;

}
