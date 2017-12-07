package tech.lapsa.insurance.esbd.entities;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Local
public interface SubjectPersonEntityService extends GeneralService<SubjectPersonEntity, Integer> {

    SubjectPersonEntity getByIIN(TaxpayerNumber taxpayerNumber) throws NotFound;
}