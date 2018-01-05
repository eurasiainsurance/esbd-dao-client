package tech.lapsa.esbd.dao.dict;

import javax.ejb.Singleton;

import tech.lapsa.esbd.dao.dict.BranchEntityService.BranchEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.BranchEntityService.BranchEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.java.commons.function.MyOptionals;

@Singleton(name = BranchEntityService.BEAN_NAME)
public class BranchEntityServiceBean extends ADictionaryEntityService<BranchEntity, Integer>
	implements BranchEntityServiceLocal, BranchEntityServiceRemote {

    private static final String DICT_NAME = "BRANCHES";

    public BranchEntityServiceBean() {
	super(DICT_NAME, BranchEntityServiceBean::convert, BranchEntityService.class);
    }

    static BranchEntity convert(final Item source) {
	final BranchEntity target = new BranchEntity();
	fillValues(source, target);
	return target;
    }

    static void fillValues(final Item source, final BranchEntity target) {
	target.id = MyOptionals.of(source.getID()).orElse(null);
	target.code = source.getCode();
	target.name = source.getName();
    }
}
