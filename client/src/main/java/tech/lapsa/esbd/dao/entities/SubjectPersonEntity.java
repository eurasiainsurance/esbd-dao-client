package tech.lapsa.esbd.dao.entities;

import com.lapsa.insurance.elements.SubjectType;

import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для представления данных о субъекте - физ.лице
 *
 * @author vadim.isaev
 *
 */
@HashCodePrime(23)
public class SubjectPersonEntity extends SubjectEntity {

    private static final long serialVersionUID = 1L;

    public static final SubjectPersonEntityBuilder builder() {
	return new SubjectPersonEntityBuilder();
    }

    public static final class SubjectPersonEntityBuilder
	    extends SubjectEntityBuilder<SubjectPersonEntity, SubjectPersonEntityBuilder> {

	private PersonalInfo personal;
	private IdentityCardInfo identityCard;

	private SubjectPersonEntityBuilder() {
	}

	public SubjectPersonEntityBuilder withPersonal(PersonalInfo personal) {
	    this.personal = personal;
	    return this;
	}

	public SubjectPersonEntityBuilder withIdentityCard(IdentityCardInfo identityCard) {
	    this.identityCard = identityCard;
	    return this;
	}

	@Override
	protected SubjectPersonEntityBuilder _this() {
	    return this;
	}

	@Override
	public SubjectPersonEntity build() throws IllegalArgumentException {
	    final SubjectPersonEntity res = new SubjectPersonEntity();
	    superFill(res);
	    res.identityCard = identityCard;
	    res.personal = personal;
	    return res;
	}
    }

    private SubjectPersonEntity() {
    }

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.PERSON;
    }

    // personal

    private PersonalInfo personal;

    public PersonalInfo getPersonal() {
	return personal;
    }

    // identityCard

    private IdentityCardInfo identityCard;

    public IdentityCardInfo getIdentityCard() {
	return identityCard;
    }
}
