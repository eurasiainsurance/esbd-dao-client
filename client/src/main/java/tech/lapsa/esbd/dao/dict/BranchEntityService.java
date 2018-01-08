package tech.lapsa.esbd.dao.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface BranchEntityService extends DictionaryEntityService<BranchEntity, Integer> {

    public static final String BEAN_NAME = "BranchEntityServiceBean";

    @Local
    public interface BranchEntityServiceLocal
	    extends DictionaryEntityServiceLocal<BranchEntity, Integer>, BranchEntityService {
    }

    @Remote
    public interface BranchEntityServiceRemote
	    extends DictionaryEntityServiceRemote<BranchEntity, Integer>, BranchEntityService {
    }
}
