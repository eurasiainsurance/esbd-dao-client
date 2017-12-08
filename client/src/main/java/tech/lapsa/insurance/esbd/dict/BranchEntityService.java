package tech.lapsa.insurance.esbd.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface BranchEntityService extends DictionaryEntityService<BranchEntity, Integer> {

    @Local
    public interface BranchEntityServiceLocal extends BranchEntityService {
    }

    @Remote
    public interface BranchEntityServiceRemote extends BranchEntityService {
    }
}
