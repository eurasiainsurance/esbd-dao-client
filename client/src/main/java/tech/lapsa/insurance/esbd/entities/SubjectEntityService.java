package tech.lapsa.insurance.esbd.entities;

import java.util.Optional;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Local
public interface SubjectEntityService extends GeneralService<SubjectEntity, Integer> {

    SubjectEntity getByIdNumber(TaxpayerNumber taxpayerNumber) throws NotFound;

    default Optional<SubjectEntity> optionalByIdNumber(TaxpayerNumber taxpayerNumber) {
	try {
	    return Optional.of(getByIdNumber(taxpayerNumber));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
