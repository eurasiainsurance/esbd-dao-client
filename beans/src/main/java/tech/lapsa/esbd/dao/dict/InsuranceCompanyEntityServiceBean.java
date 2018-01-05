package tech.lapsa.esbd.dao.dict;

import javax.ejb.Stateless;

import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.java.commons.function.MyOptionals;

@Stateless(name = InsuranceCompanyEntityService.BEAN_NAME)
public class InsuranceCompanyEntityServiceBean extends ADictionaryEntityService<InsuranceCompanyEntity, Integer>
	implements InsuranceCompanyEntityServiceLocal, InsuranceCompanyEntityServiceRemote {

    private static final String DICT_NAME = "INSURANCE_COMPANIES";

    public InsuranceCompanyEntityServiceBean() {
	super(DICT_NAME, InsuranceCompanyEntityServiceBean::convert, InsuranceCompanyEntityService.class);
    }

    static InsuranceCompanyEntity convert(final Item source) {
	final InsuranceCompanyEntity target = new InsuranceCompanyEntity();
	fillValues(source, target);
	return target;
    }

    static void fillValues(final Item source, final InsuranceCompanyEntity target) {
	target.id = MyOptionals.of(source.getID()).orElse(null);
	target.code = source.getCode();
	target.name = source.getName();
    }
}
