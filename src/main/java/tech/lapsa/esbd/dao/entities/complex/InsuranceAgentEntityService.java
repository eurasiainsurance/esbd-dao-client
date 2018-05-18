package tech.lapsa.esbd.dao.entities.complex;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.AEntityService;

public interface InsuranceAgentEntityService extends AEntityService<InsuranceAgentEntity, Integer> {

    public static final String BEAN_NAME = "InsuranceAgentEntityServiceBean";

    @Local
    public interface InsuranceAgentEntityServiceLocal
	    extends AEntityServiceLocal<InsuranceAgentEntity, Integer>, InsuranceAgentEntityService {
    }

    @Remote
    public interface InsuranceAgentEntityServiceRemote
	    extends AEntityServiceRemote<InsuranceAgentEntity, Integer>, InsuranceAgentEntityService {
    }
}
