package tech.lapsa.esbd.dao.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface BranchEntityService extends DictionaryEntityService<BranchEntity, Integer> {

    public static final String BEAN_NAME = "BranchEntityServiceBean";

    @Local
    public interface BranchEntityServiceLocal extends BranchEntityService {
    }

    @Remote
    public interface BranchEntityServiceRemote extends BranchEntityService {
    }
}
