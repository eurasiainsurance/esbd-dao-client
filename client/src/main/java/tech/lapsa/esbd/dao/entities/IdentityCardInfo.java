package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(59)
public class IdentityCardInfo extends Domain {

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
	    this.issuingAuthority = issuingAuthority;
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
	    return new IdentityCardInfo(dateOfIssue, issuingAuthority, number, identityCardType);
	}

	public void buildTo(final Consumer<IdentityCardInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private IdentityCardInfo(final LocalDate dateOfIssue, final String issuingAuthority, final String number,
	    final IdentityCardType identityCardType) {
	this.dateOfIssue = MyObjects.requireNonNull(dateOfIssue, "dateOfIssue");
	this.issuingAuthority = issuingAuthority;
	this.number = MyStrings.requireNonEmpty(number, "number");
	this.identityCardType = MyObjects.requireNonNull(identityCardType, "identityCardType");
    }

    // DOCUMENT_GIVED_DATE s:string Дата выдачи документа
    private final LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // DOCUMENT_GIVED_BY s:string Документ выдан
    private final String issuingAuthority;

    public String getIssuingAuthority() {
	return issuingAuthority;
    }

    // DOCUMENT_NUMBER s:string Номер документа
    private final String number;

    public String getNumber() {
	return number;
    }

    // DOCUMENT_TYPE_ID s:int Тип документа (справочник DOCUMENTS_TYPES)
    private final IdentityCardType identityCardType;

    public IdentityCardType getIdentityCardType() {
	return identityCardType;
    }
}
