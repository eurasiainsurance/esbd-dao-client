package tech.lapsa.insurance.esbd.entities;

import java.util.Optional;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;

@Local
public interface PolicyEntityService extends GeneralService<PolicyEntity, Integer> {

    PolicyEntity getByNumber(String number) throws NotFound;

    default Optional<PolicyEntity> optionalByNumber(String number) {
	try {
	    return Optional.of(getByNumber(number));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
