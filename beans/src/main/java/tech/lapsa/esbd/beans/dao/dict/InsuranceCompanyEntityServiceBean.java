package tech.lapsa.esbd.dao.dict;

import javax.ejb.Stateless;

import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceRemote;

@Stateless(name = InsuranceCompanyEntityService.BEAN_NAME)
public class InsuranceCompanyEntityServiceBean
	extends ADictionaryEntityService<InsuranceCompanyEntity>
	implements InsuranceCompanyEntityServiceLocal, InsuranceCompanyEntityServiceRemote {

    private static final String DICT_NAME = "INSURANCE_COMPANIES";

    public InsuranceCompanyEntityServiceBean() {
	super(InsuranceCompanyEntityService.class, DICT_NAME, InsuranceCompanyEntity::builder);
    }
}
