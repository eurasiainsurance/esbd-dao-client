package tech.lapsa.esbd.dao.beans.dict;

import javax.ejb.Singleton;

import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.BranchEntityService;
import tech.lapsa.esbd.dao.dict.BranchEntityService.BranchEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.BranchEntityService.BranchEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.Item;

@Singleton(name = BranchEntityService.BEAN_NAME)
public class BranchEntityServiceBean extends ADictionaryEntityService<BranchEntity, Integer>
	implements BranchEntityServiceLocal, BranchEntityServiceRemote {

    private static final String DICT_NAME = "BRANCHES";

    public BranchEntityServiceBean() {
	super(DICT_NAME, BranchEntityServiceBean::convert, BranchEntityService.class);
    }

    private static BranchEntity convert(final Item source) {
	final BranchEntity target = new BranchEntity();
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
	return target;
    }
}
