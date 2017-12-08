package tech.lapsa.insurance.esbd.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface InsuranceCompanyEntityService extends DictionaryEntityService<InsuranceCompanyEntity, Integer> {

    @Local
    public interface InsuranceCompanyEntityServiceLocal extends InsuranceCompanyEntityService {
    }

    @Remote
    public interface InsuranceCompanyEntityServiceRemote extends InsuranceCompanyEntityService {
    }
}
