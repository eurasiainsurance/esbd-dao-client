package tech.lapsa.esbd.dao.entities.complex;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.ICachableEntitiesService;
import tech.lapsa.esbd.domain.complex.InsuranceAgentEntity;

public interface InsuranceAgentEntityService extends ICachableEntitiesService<InsuranceAgentEntity> {

    public static final String BEAN_NAME = "InsuranceAgentEntityServiceBean";

    @Local
    public interface InsuranceAgentEntityServiceLocal
	    extends ICachableEntityServiceLocal<InsuranceAgentEntity>, InsuranceAgentEntityService {
    }

    @Remote
    public interface InsuranceAgentEntityServiceRemote
	    extends ICachableEntityServiceRemote<InsuranceAgentEntity>, InsuranceAgentEntityService {
    }
}
