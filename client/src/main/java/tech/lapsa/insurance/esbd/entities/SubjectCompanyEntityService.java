package tech.lapsa.insurance.esbd.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface SubjectCompanyEntityService extends GeneralService<SubjectCompanyEntity, Integer> {

    @Local
    public interface SubjectCompanyEntityServiceLocal extends SubjectCompanyEntityService {
    }

    @Remote
    public interface SubjectCompanyEntityServiceRemote extends SubjectCompanyEntityService {
    }

    SubjectCompanyEntity getByBIN(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;

}
