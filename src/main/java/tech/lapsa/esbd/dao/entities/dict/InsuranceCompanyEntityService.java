package tech.lapsa.esbd.dao.entities.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface InsuranceCompanyEntityService extends ADictEntityService<InsuranceCompanyEntity> {

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
