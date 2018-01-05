package tech.lapsa.esbd.dao.dict;

import javax.ejb.Stateless;

import tech.lapsa.esbd.dao.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.java.commons.function.MyOptionals;

@Stateless(name = CompanyActivityKindEntityService.BEAN_NAME)
public class CompanyActivityKindEntityServiceBean extends ADictionaryEntityService<CompanyActivityKindEntity, Integer>
	implements CompanyActivityKindEntityServiceLocal, CompanyActivityKindEntityServiceRemote {

    private static final String DICT_NAME = "ACTIVITY_KINDS";

    public CompanyActivityKindEntityServiceBean() {
	super(DICT_NAME, CompanyActivityKindEntityServiceBean::convert, CompanyActivityKindEntityService.class);
    }

    static CompanyActivityKindEntity convert(final Item source) {
	final CompanyActivityKindEntity target = new CompanyActivityKindEntity();
	fillValues(source, target);
	return target;
    }

    static void fillValues(final Item source, final CompanyActivityKindEntity target) {
	target.id = MyOptionals.of(source.getID()).orElse(null);
	target.code = source.getCode();
	target.name = source.getName();
    }
}
