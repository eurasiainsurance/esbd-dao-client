package tech.lapsa.insurance.esbd.elements;

import java.time.LocalDate;
import java.util.Optional;

import javax.ejb.Local;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;

@Local
public interface InsuranceClassTypeService extends ElementsService<InsuranceClassType, Integer> {

    InsuranceClassType getDefault();

    InsuranceClassType getByCode(String code) throws NotFound;

    default Optional<InsuranceClassType> optionalByCode(String code) {
	try {
	    return Optional.of(getByCode(code));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }

    InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson, LocalDate date) throws NotFound;

    default Optional<InsuranceClassType> optionalForSubject(SubjectPersonEntity subjectPerson, LocalDate date) {
	try {
	    return Optional.of(getForSubject(subjectPerson, date));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }

    InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson) throws NotFound;

    default Optional<InsuranceClassType> optionalForSubject(SubjectPersonEntity subjectPerson) {
	try {
	    return Optional.of(getForSubject(subjectPerson));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
