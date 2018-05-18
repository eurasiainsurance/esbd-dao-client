package tech.lapsa.esbd.dao.entities.dict;

import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(5)
public class CompanyActivityKindEntity extends ADictEntity {

    private static final long serialVersionUID = 1L;

    public static final CompanyActivityKindEntityBuilder builder() {
	return new CompanyActivityKindEntityBuilder();
    }

    public static final class CompanyActivityKindEntityBuilder
	    extends DictionaryEntityBuilder<CompanyActivityKindEntity> {
	@Override
	public CompanyActivityKindEntity build() {
	    return new CompanyActivityKindEntity(id, code, name);
	}
    }

    private CompanyActivityKindEntity(final Integer id, final String code, final String name) {
	super(id, code, name);
    }

}
