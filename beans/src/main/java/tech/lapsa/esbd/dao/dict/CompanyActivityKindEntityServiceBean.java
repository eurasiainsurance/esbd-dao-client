package tech.lapsa.esbd.dao.beans.dict;

import javax.ejb.Stateless;

import tech.lapsa.esbd.dao.dict.CompanyActivityKindEntity;
import tech.lapsa.esbd.dao.dict.CompanyActivityKindEntityService;
import tech.lapsa.esbd.dao.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.Item;

@Stateless(name = CompanyActivityKindEntityService.BEAN_NAME)
public class CompanyActivityKindEntityServiceBean extends ADictionaryEntityService<CompanyActivityKindEntity, Integer>
	implements CompanyActivityKindEntityServiceLocal, CompanyActivityKindEntityServiceRemote {

    private static final String DICT_NAME = "ACTIVITY_KINDS";

    public CompanyActivityKindEntityServiceBean() {
	super(DICT_NAME, CompanyActivityKindEntityServiceBean::convert, CompanyActivityKindEntityService.class);
    }

    private static CompanyActivityKindEntity convert(final Item source) {
	final CompanyActivityKindEntity target = new CompanyActivityKindEntity();
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
	return target;
    }
}
