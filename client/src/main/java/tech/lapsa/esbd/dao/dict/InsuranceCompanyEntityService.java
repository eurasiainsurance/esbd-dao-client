package tech.lapsa.esbd.dao.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface InsuranceCompanyEntityService
	extends DictionaryEntityService<InsuranceCompanyEntity, Integer> {

    public static final String BEAN_NAME = "InsuranceCompanyEntityServiceBean";

    @Local
    public interface InsuranceCompanyEntityServiceLocal
	    extends DictionaryEntityServiceLocal<InsuranceCompanyEntity, Integer>, InsuranceCompanyEntityService {
    }

    @Remote
    public interface InsuranceCompanyEntityServiceRemote
	    extends DictionaryEntityServiceRemote<InsuranceCompanyEntity, Integer>, InsuranceCompanyEntityService {
    }
}
