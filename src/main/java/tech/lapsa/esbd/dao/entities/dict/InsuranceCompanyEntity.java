package tech.lapsa.esbd.dao.entities.dict;

import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(7)
public class InsuranceCompanyEntity extends ADictEntity {

    private static final long serialVersionUID = 1L;

    public static final InsuranceCompanyEntityBuilder builder() {
	return new InsuranceCompanyEntityBuilder();
    }

    public static final class InsuranceCompanyEntityBuilder extends DictionaryEntityBuilder<InsuranceCompanyEntity> {
	@Override
	public InsuranceCompanyEntity build() {
	    return new InsuranceCompanyEntity(id, code, name);
	}
    }

    private InsuranceCompanyEntity(final Integer id, final String code, final String name) {
	super(id, code, name);
    }

}
