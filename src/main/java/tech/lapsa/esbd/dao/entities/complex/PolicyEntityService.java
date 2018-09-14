package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.entities.ICachableEntitiesService;
import tech.lapsa.esbd.domain.complex.PolicyEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface PolicyEntityService extends ICachableEntitiesService<PolicyEntity> {

    public static final String BEAN_NAME = "PolicyEntityServiceBean";

    @Local
    public interface PolicyEntityServiceLocal
	    extends ICachableEntityServiceLocal<PolicyEntity>, PolicyEntityService {
    }

    @Remote
    public interface PolicyEntityServiceRemote
	    extends ICachableEntityServiceRemote<PolicyEntity>, PolicyEntityService {
    }

    PolicyEntity getByNumber(String number) throws IllegalArgument, NotFound;

    List<PolicyEntity> getByInternalNumber(String internalNumber) throws IllegalArgument;
}
