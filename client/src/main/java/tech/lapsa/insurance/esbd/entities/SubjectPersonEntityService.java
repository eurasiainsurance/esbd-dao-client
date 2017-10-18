package tech.lapsa.insurance.esbd.entities;

import java.util.Optional;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;

@Local
public interface SubjectPersonEntityService extends GeneralService<SubjectPersonEntity, Integer> {

    SubjectPersonEntity getByIIN(String iin) throws NotFound;

    default Optional<SubjectPersonEntity> optionalByIIN(String iin) {
	try {
	    return Optional.of(getByIIN(iin));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
