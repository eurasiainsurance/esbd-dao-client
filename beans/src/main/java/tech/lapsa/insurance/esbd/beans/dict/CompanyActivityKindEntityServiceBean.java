package tech.lapsa.insurance.esbd.beans.dict;

import javax.ejb.Stateless;

import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntity;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceLocal;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceRemote;

@Stateless(name = CompanyActivityKindEntityService.BEAN_NAME)
public class CompanyActivityKindEntityServiceBean extends ADictionaryEntityService<CompanyActivityKindEntity, Integer>
	implements CompanyActivityKindEntityServiceLocal, CompanyActivityKindEntityServiceRemote {

    private static final String DICT_NAME = "ACTIVITY_KINDS";

    public CompanyActivityKindEntityServiceBean() {
	super(DICT_NAME, CompanyActivityKindEntityServiceBean::convert);
    }

    private static CompanyActivityKindEntity convert(final Item source) {
	final CompanyActivityKindEntity target = new CompanyActivityKindEntity();
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
	return target;
    }
}
