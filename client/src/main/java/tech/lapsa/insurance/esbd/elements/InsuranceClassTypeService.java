package tech.lapsa.insurance.esbd.elements;

import java.time.LocalDate;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;

public interface InsuranceClassTypeService extends ElementsService<InsuranceClassType, Integer> {

    @Local
    public interface InsuranceClassTypeServiceLocal extends InsuranceClassTypeService {
    }

    @Remote
    public interface InsuranceClassTypeServiceRemote extends InsuranceClassTypeService {
    }

    InsuranceClassType getDefault();

    InsuranceClassType getByCode(String code) throws NotFound;

    InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson, LocalDate date) throws NotFound;

    InsuranceClassType getForSubject(SubjectPersonEntity subjectPerson) throws NotFound;

}
