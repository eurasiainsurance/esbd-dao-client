package tech.lapsa.esbd.dao.entities.complex;

import com.lapsa.insurance.elements.SubjectType;
import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.entities.embeded.ContactInfo;
import tech.lapsa.esbd.dao.entities.embeded.IdentityCardInfo;
import tech.lapsa.esbd.dao.entities.embeded.OriginInfo;
import tech.lapsa.esbd.dao.entities.embeded.PersonalInfo;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

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
	    return new SubjectPersonEntity(id,
		    origin,
		    contact,
		    taxPayerNumber,
		    comments,
		    resident,
		    idNumber,
		    economicsSector,
		    personal,
		    identityCard);
	}
    }

    // constructor

    private SubjectPersonEntity(final Integer id,
	    final OriginInfo origin,
	    final ContactInfo contact,
	    final String taxPayerNumber,
	    final String comments,
	    final Boolean resident,
	    final TaxpayerNumber idNumber,
	    final KZEconomicSector economicsSector,
	    final PersonalInfo personal,
	    final IdentityCardInfo identityCard) {
	super(id, origin, contact, taxPayerNumber, comments, resident, idNumber, economicsSector);
	this.personal = personal;
	this.identityCard = identityCard;
    }

    // subjectType

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.PERSON;
    }

    // personal

    private final PersonalInfo personal;

    public PersonalInfo getPersonal() {
	return personal;
    }

    // identityCard

    private final IdentityCardInfo identityCard;

    public IdentityCardInfo getIdentityCard() {
	return identityCard;
    }
}
