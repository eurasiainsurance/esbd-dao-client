package tech.lapsa.esbd.dao.entities.complex;

import com.lapsa.insurance.elements.SubjectType;

import tech.lapsa.esbd.dao.entities.embeded.IdentityCardInfo;
import tech.lapsa.esbd.dao.entities.embeded.PersonalInfo;
import tech.lapsa.java.commons.function.MyObjects;
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

	public SubjectPersonEntityBuilder withPersonal(final PersonalInfo personal) {
	    this.personal = MyObjects.requireNonNull(personal, "personal");
	    return this;
	}

	public SubjectPersonEntityBuilder withIdentityCard(final IdentityCardInfo identityCard) {
	    this.identityCard = MyObjects.requireNonNull(identityCard, "identityCard");
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
	    res.identityCard = MyObjects.requireNonNull(identityCard, "identityCard");
	    res.personal = MyObjects.requireNonNull(personal, "personal");
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
