package tech.lapsa.esbd.dao.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.GeneralService;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface SubjectEntityService extends GeneralService<SubjectEntity, Integer> {

    public static final String BEAN_NAME = "SubjectEntityServiceBean";

    @Local
    public interface SubjectEntityServiceLocal extends SubjectEntityService {
    }

    @Remote
    public interface SubjectEntityServiceRemote extends SubjectEntityService {
    }

    SubjectEntity getByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;
}
