package tech.lapsa.insurance.esbd.entities;

import java.util.Optional;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;

@Local
public interface SubjectCompanyEntityService extends GeneralService<SubjectCompanyEntity, Integer> {

    SubjectCompanyEntity getByBIN(String bin) throws NotFound;

    default Optional<SubjectCompanyEntity> optionalByBIN(String bin) {
	try {
	    return Optional.of(getByBIN(bin));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
