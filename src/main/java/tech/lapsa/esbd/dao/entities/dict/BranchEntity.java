package tech.lapsa.esbd.dao.entities.dict;

import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(3)
public class BranchEntity extends ADictEntity {

    private static final long serialVersionUID = 1L;

    public static final BranchEntityBuilder builder() {
	return new BranchEntityBuilder();
    }

    public static final class BranchEntityBuilder extends DictionaryEntityBuilder<BranchEntity> {
	@Override
	public BranchEntity build() {
	    return new BranchEntity(id, code, name);
	}
    }

    private BranchEntity(final Integer id, final String code, final String name) {
	super(id, code, name);
    }
}
