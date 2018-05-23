package tech.lapsa.esbd.dao.entities.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.IDictEntitiesService;
import tech.lapsa.esbd.domain.dict.InsuranceCompanyEntity;

public interface InsuranceCompanyEntityService extends IDictEntitiesService<InsuranceCompanyEntity> {

    public static final String BEAN_NAME = "InsuranceCompanyEntityServiceBean";

    @Local
    public interface InsuranceCompanyEntityServiceLocal
	    extends ADictEntityServiceLocal<InsuranceCompanyEntity>, InsuranceCompanyEntityService {
    }

    @Remote
    public interface InsuranceCompanyEntityServiceRemote
	    extends ADictEntityServiceRemote<InsuranceCompanyEntity>, InsuranceCompanyEntityService {
    }
}
