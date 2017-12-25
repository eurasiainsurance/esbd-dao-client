package tech.lapsa.esbd.dao.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.GeneralService;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface SubjectCompanyEntityService extends GeneralService<SubjectCompanyEntity, Integer> {

    public static final String BEAN_NAME = "SubjectCompanyEntityServiceBean";

    @Local
    public interface SubjectCompanyEntityServiceLocal extends SubjectCompanyEntityService {
    }

    @Remote
    public interface SubjectCompanyEntityServiceRemote extends SubjectCompanyEntityService {
    }

    SubjectCompanyEntity getByBIN(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;

}
