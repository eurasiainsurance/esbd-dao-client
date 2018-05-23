package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.IEntitiesService;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.domain.complex.PolicyEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface PolicyEntityService extends IEntitiesService<PolicyEntity> {

    public static final String BEAN_NAME = "PolicyEntityServiceBean";

    @Local
    public interface PolicyEntityServiceLocal
	    extends IEntityServiceLocal<PolicyEntity>, PolicyEntityService {
    }

    @Remote
    public interface PolicyEntityServiceRemote
	    extends IEntityServiceRemote<PolicyEntity>, PolicyEntityService {
    }

    PolicyEntity getByNumber(String number) throws IllegalArgument, NotFound;

    List<PolicyEntity> getByInternalNumber(String internalNumber) throws IllegalArgument;
}
