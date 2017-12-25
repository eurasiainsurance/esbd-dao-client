package tech.lapsa.esbd.dao.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.GeneralService;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface SubjectPersonEntityService extends GeneralService<SubjectPersonEntity, Integer> {

    public static final String BEAN_NAME = "SubjectPersonEntityServiceBean";

    @Local
    public interface SubjectPersonEntityServiceLocal extends SubjectPersonEntityService {
    }

    @Remote
    public interface SubjectPersonEntityServiceRemote extends SubjectPersonEntityService {
    }

    SubjectPersonEntity getByIIN(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;
}