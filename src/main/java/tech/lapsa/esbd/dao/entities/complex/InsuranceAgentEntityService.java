package tech.lapsa.esbd.dao.entities.complex;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.IEntitiesService;
import tech.lapsa.esbd.domain.complex.InsuranceAgentEntity;

public interface InsuranceAgentEntityService extends IEntitiesService<InsuranceAgentEntity> {

    public static final String BEAN_NAME = "InsuranceAgentEntityServiceBean";

    @Local
    public interface InsuranceAgentEntityServiceLocal
	    extends IEntityServiceLocal<InsuranceAgentEntity>, InsuranceAgentEntityService {
    }

    @Remote
    public interface InsuranceAgentEntityServiceRemote
	    extends IEntityServiceRemote<InsuranceAgentEntity>, InsuranceAgentEntityService {
    }
}
