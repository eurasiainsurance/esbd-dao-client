package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.Optional;
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

	public IdentityCardInfoBuilder withDateOfIssue(final Optional<LocalDate> optDateOfIssue) {
	    if (MyObjects.requireNonNull(optDateOfIssue, "optDateOfIssue").isPresent())
		return withDateOfIssue(optDateOfIssue.get());
	    dateOfIssue = null;
	    return this;
	}

	public IdentityCardInfoBuilder withIssuingAuthority(final String issuingAuthority) {
	    this.issuingAuthority = issuingAuthority;
	    return this;
	}

	public IdentityCardInfoBuilder withNumber(final String number) {
	    this.number = MyStrings.requireNonEmpty(number, "number");
	    return this;
	}

	public IdentityCardInfoBuilder withNumber(final Optional<String> optNumber) {
	    if (MyObjects.requireNonNull(optNumber, "optNumber").isPresent())
		return withNumber(optNumber.get());
	    number = null;
	    return this;
	}

	public IdentityCardInfoBuilder withIdentityCardType(final IdentityCardType identityCardType) {
	    this.identityCardType = MyObjects.requireNonNull(identityCardType, "identityCardType");
	    return this;
	}

	public IdentityCardInfoBuilder withIdentityCardType(final Optional<IdentityCardType> optIdentityCardType) {
	    if (MyObjects.requireNonNull(optIdentityCardType, "optIdentityCardType").isPresent())
		return withIdentityCardType(optIdentityCardType.get());
	    identityCardType = null;
	    return this;
	}

	public IdentityCardInfo build() {
	    final IdentityCardInfo res = new IdentityCardInfo();
	    res.dateOfIssue = dateOfIssue;
	    res.issuingAuthority = issuingAuthority;
	    res.number = number;
	    res.identityCardType = identityCardType;
	    return res;
	}

	public void buildTo(final Consumer<IdentityCardInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private IdentityCardInfo() {
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
