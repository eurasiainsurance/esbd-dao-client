package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(59)
public class IdentityCardInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final IdentityCardInfoBuilder builder() {
	return new IdentityCardInfoBuilder();
    }

    public static final class IdentityCardInfoBuilder {

	private LocalDate dateOfIssue;
	private String issuingAuthority;
	private String number;
	private IdentityCardType identityCardType;

	private IdentityCardInfoBuilder() {
	}

	public IdentityCardInfoBuilder withDateOfIssue(final LocalDate dateOfIssue) {
	    this.dateOfIssue = MyObjects.requireNonNull(dateOfIssue, "dateOfIssue");
	    return this;
	}

	public IdentityCardInfoBuilder withIssuingAuthority(final String issuingAuthority) {
	    this.issuingAuthority = MyStrings.requireNonEmpty(issuingAuthority, "issuingAuthority");
	    return this;
	}

	public IdentityCardInfoBuilder withNumber(final String number) {
	    this.number = MyStrings.requireNonEmpty(number, "number");
	    return this;
	}

	public IdentityCardInfoBuilder withIdentityCardType(final IdentityCardType identityCardType) {
	    this.identityCardType = MyObjects.requireNonNull(identityCardType, "identityCardType");
	    return this;
	}

	public IdentityCardInfo build() {
	    return new IdentityCardInfo(dateOfIssue,
		    issuingAuthority,
		    number,
		    identityCardType);
	}

	public void buildTo(final Consumer<IdentityCardInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private IdentityCardInfo(final LocalDate dateOfIssue,
	    final String issuingAuthority,
	    final String number,
	    final IdentityCardType identityCardType) {
	this.dateOfIssue = dateOfIssue;
	this.issuingAuthority = issuingAuthority;
	this.number = number;
	this.identityCardType = identityCardType;
    }

    // dateOfIssue

    private LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // issuingAuthority

    private String issuingAuthority;

    public String getIssuingAuthority() {
	return issuingAuthority;
    }

    // number

    private String number;

    public String getNumber() {
	return number;
    }

    // identityCardType

    private IdentityCardType identityCardType;

    public IdentityCardType getIdentityCardType() {
	return identityCardType;
    }
}
