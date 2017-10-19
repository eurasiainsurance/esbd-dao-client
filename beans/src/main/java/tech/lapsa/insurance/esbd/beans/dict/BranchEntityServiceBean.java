package tech.lapsa.insurance.esbd.beans.dict;

import javax.ejb.Singleton;

import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.insurance.esbd.dict.BranchEntity;
import tech.lapsa.insurance.esbd.dict.BranchEntityService;

@Singleton
public class BranchEntityServiceBean extends ADictionaryEntityService<BranchEntity, Integer>
	implements BranchEntityService {

    private static final String DICT_NAME = "BRANCHES";

    public BranchEntityServiceBean() {
	super(DICT_NAME, BranchEntityServiceBean::convert);
    }

    private static BranchEntity convert(Item source) {
	BranchEntity target = new BranchEntity();
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
	return target;
    }
}
