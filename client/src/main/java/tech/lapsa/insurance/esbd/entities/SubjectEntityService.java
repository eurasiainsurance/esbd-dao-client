package tech.lapsa.insurance.esbd.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface SubjectEntityService extends GeneralService<SubjectEntity, Integer> {

    @Local
    public interface SubjectEntityServiceLocal extends SubjectEntityService {
    }

    @Remote
    public interface SubjectEntityServiceRemote extends SubjectEntityService {
    }

    SubjectEntity getByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;
}
