package tech.lapsa.esbd.dao.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.domain.dict.BranchEntity;

public interface BranchEntityService extends DictionaryEntityService<BranchEntity> {

    public static final String BEAN_NAME = "BranchEntityServiceBean";

    @Local
    public interface BranchEntityServiceLocal
	    extends DictionaryEntityServiceLocal<BranchEntity>, BranchEntityService {
    }

    @Remote
    public interface BranchEntityServiceRemote
	    extends DictionaryEntityServiceRemote<BranchEntity>, BranchEntityService {
    }
}
