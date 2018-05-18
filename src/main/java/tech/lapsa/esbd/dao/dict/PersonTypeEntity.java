package tech.lapsa.esbd.dao.dict;

import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(97)
public class PersonTypeEntity extends DictionaryEntity {

    private static final long serialVersionUID = 1L;

    public static final PersonTypeEntityBuilder builder() {
	return new PersonTypeEntityBuilder();
    }

    public static final class PersonTypeEntityBuilder extends DictionaryEntityBuilder<PersonTypeEntity> {
	@Override
	public PersonTypeEntity build() {
	    return new PersonTypeEntity(id, code, name);
	}
    }

    private PersonTypeEntity(final Integer id, final String code, final String name) {
	super(id, code, name);
    }
}
