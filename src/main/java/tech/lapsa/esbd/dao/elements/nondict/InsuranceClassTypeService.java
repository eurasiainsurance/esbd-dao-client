package tech.lapsa.esbd.dao.elements.nondict;

import java.time.LocalDate;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.ElementsService;
import tech.lapsa.esbd.domain.complex.SubjectPersonEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface InsuranceClassTypeService extends ElementsService<InsuranceClassType> {

    public static final String BEAN_NAME = "InsuranceClassTypeServiceBean";

    @Local
    public interface InsuranceClassTypeServiceLocal
	    extends ElementsServiceLocal<InsuranceClassType>, InsuranceClassTypeService {
    }

    @Remote
    public interface InsuranceClassTypeServiceRemote
	    extends ElementsServiceRemote<InsuranceClassType>, InsuranceClassTypeService {
    }

    InsuranceClassType getDefault();

    InsuranceClassType getByCode(String code) throws IllegalArgument, NotFound;

    InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson, LocalDate date)
	    throws IllegalArgument, NotFound;

    InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson) throws IllegalArgument, NotFound;

}
