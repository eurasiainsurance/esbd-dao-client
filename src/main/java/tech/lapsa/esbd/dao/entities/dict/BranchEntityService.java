package tech.lapsa.esbd.dao.entities.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.domain.dict.BranchEntity;

public interface BranchEntityService extends ADictEntityService<BranchEntity> {

    public static final String BEAN_NAME = "BranchEntityServiceBean";

    @Local
    public interface BranchEntityServiceLocal
	    extends ADictEntityServiceLocal<BranchEntity>, BranchEntityService {
    }

    @Remote
    public interface BranchEntityServiceRemote
	    extends ADictEntityServiceRemote<BranchEntity>, BranchEntityService {
    }
}
