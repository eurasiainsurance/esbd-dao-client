package tech.lapsa.insurance.esbd.entities;

import java.util.Optional;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.NotFound;

@Local
public interface SubjectEntityService {

    SubjectEntity getById(Integer id) throws NotFound;

    default Optional<SubjectEntity> optionalById(Integer id) {
	try {
	    return Optional.of(getById(id));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }

    SubjectEntity getByIdNumber(String idNumber) throws NotFound;

    default Optional<SubjectEntity> optionalByIdNumber(String idNumber) {
	try {
	    return Optional.of(getByIdNumber(idNumber));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
