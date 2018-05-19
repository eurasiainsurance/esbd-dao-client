package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(61)
public class HandicappedInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final HandicappedInfoBuilder builder() {
	return new HandicappedInfoBuilder();
    }

    public static final class HandicappedInfoBuilder {

	private String certificateNumber;
	private LocalDate certificateValidFrom;
	private LocalDate certificateValidTill;

	private HandicappedInfoBuilder() {
	}

	public HandicappedInfoBuilder withCertificateNumber(final String certificateNumber) {
	    this.certificateNumber = MyStrings.requireNonEmpty(certificateNumber, "certificateNumber");
	    return this;
	}

	public HandicappedInfoBuilder withCertificateValidFrom(final LocalDate certificateValidFrom) {
	    this.certificateValidFrom = MyObjects.requireNonNull(certificateValidFrom, "certificateValidFrom");
	    return this;
	}

	public HandicappedInfoBuilder withCertificateValidTill(final LocalDate certificateValidTill) {
	    this.certificateValidTill = MyObjects.requireNonNull(certificateValidTill, "certificateValidTill");
	    return this;
	}

	public HandicappedInfo build() {
	    final HandicappedInfo res = new HandicappedInfo();
	    res.certificateNumber = MyStrings.requireNonEmpty(certificateNumber, "certificateNumber");
	    res.certificateValidFrom = MyObjects.requireNonNull(certificateValidFrom, "certificateValidFrom");
	    res.certificateValidTill = MyObjects.requireNonNull(certificateValidTill, "certificateValidTill");
	    return res;
	}

	public void buildTo(final Consumer<HandicappedInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private HandicappedInfo() {
    }

    // certificateNumber

    private String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    // certificateValidFrom

    private LocalDate certificateValidFrom;

    public LocalDate getCertificateValidFrom() {
	return certificateValidFrom;
    }

    // certificateValidTill

    private LocalDate certificateValidTill;

    public LocalDate getCertificateValidTill() {
	return certificateValidTill;
    }
}
