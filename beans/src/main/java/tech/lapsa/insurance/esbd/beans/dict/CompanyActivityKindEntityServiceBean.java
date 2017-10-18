package tech.lapsa.insurance.esbd.beans.dict;

import javax.ejb.Stateless;

import com.lapsa.esbd.jaxws.client.Item;

import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntity;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService;

@Stateless
public class CompanyActivityKindEntityServiceBean extends ADictionaryEntityService<CompanyActivityKindEntity, Integer>
	implements CompanyActivityKindEntityService {

    private static final String DICT_NAME = "ACTIVITY_KINDS";

    public CompanyActivityKindEntityServiceBean() {
	super(DICT_NAME, CompanyActivityKindEntityServiceBean::convert);
    }

    private static CompanyActivityKindEntity convert(Item source) {
	CompanyActivityKindEntity target = new CompanyActivityKindEntity();
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
	return target;
    }
}
