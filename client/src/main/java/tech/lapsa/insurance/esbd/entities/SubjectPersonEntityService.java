package tech.lapsa.insurance.esbd.entities;

import java.util.Optional;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Local
public interface SubjectPersonEntityService extends GeneralService<SubjectPersonEntity, Integer> {

    SubjectPersonEntity getByIIN(TaxpayerNumber taxpayerNumber) throws NotFound;

    default Optional<SubjectPersonEntity> optionalByIIN(TaxpayerNumber taxpayerNumber) {
	try {
	    return Optional.of(getByIIN(taxpayerNumber));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
