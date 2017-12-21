package tech.lapsa.insurance.esbd.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface PolicyEntityService extends GeneralService<PolicyEntity, Integer> {

    public static final String BEAN_NAME = "PolicyEntityServiceBean";

    @Local
    public interface PolicyEntityServiceLocal extends PolicyEntityService {
    }

    @Remote
    public interface PolicyEntityServiceRemote extends PolicyEntityService {
    }

    PolicyEntity getByNumber(String number) throws IllegalArgument, NotFound;
}
