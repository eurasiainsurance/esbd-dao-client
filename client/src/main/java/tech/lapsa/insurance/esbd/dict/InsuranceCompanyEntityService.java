package tech.lapsa.insurance.esbd.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface InsuranceCompanyEntityService
	extends DictionaryEntityService<InsuranceCompanyEntity, Integer> {

    public static final String BEAN_NAME = "InsuranceCompanyEntityServiceBean";

    @Local
    public interface InsuranceCompanyEntityServiceLocal extends InsuranceCompanyEntityService {
    }

    @Remote
    public interface InsuranceCompanyEntityServiceRemote extends InsuranceCompanyEntityService {
    }
}
