package tech.lapsa.insurance.esbd.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface CompanyActivityKindEntityService extends DictionaryEntityService<CompanyActivityKindEntity, Integer> {

    @Local
    public interface CompanyActivityKindEntityServiceLocal extends CompanyActivityKindEntityService {
    }

    @Remote
    public interface CompanyActivityKindEntityServiceRemote extends CompanyActivityKindEntityService {
    }
}
