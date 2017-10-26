package tech.lapsa.insurance.esbd.entities;

import java.util.Optional;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Local
public interface SubjectCompanyEntityService extends GeneralService<SubjectCompanyEntity, Integer> {

    SubjectCompanyEntity getByBIN(TaxpayerNumber taxpayerNumber) throws NotFound;

    default Optional<SubjectCompanyEntity> optionalByBIN(TaxpayerNumber taxpayerNumber) {
	try {
	    return Optional.of(getByBIN(taxpayerNumber));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
