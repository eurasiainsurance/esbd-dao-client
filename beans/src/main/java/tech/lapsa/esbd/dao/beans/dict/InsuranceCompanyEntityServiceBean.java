package tech.lapsa.esbd.dao.beans.dict;

import javax.ejb.Stateless;

import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.Item;

@Stateless(name = InsuranceCompanyEntityService.BEAN_NAME)
public class InsuranceCompanyEntityServiceBean extends ADictionaryEntityService<InsuranceCompanyEntity, Integer>
	implements InsuranceCompanyEntityServiceLocal, InsuranceCompanyEntityServiceRemote {

    private static final String DICT_NAME = "INSURANCE_COMPANIES";

    public InsuranceCompanyEntityServiceBean() {
	super(DICT_NAME, InsuranceCompanyEntityServiceBean::convert);
    }

    private static InsuranceCompanyEntity convert(final Item source) {
	final InsuranceCompanyEntity target = new InsuranceCompanyEntity();
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
	return target;
    }
}
