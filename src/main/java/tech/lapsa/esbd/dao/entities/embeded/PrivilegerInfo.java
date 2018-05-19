package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(79)
public class PrivilegerInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final PrivilegerInfoBuilder builder() {
	return new PrivilegerInfoBuilder();
    }

    public static final class PrivilegerInfoBuilder {

	private String type;
	private String certificateNumber;
	private LocalDate certificateDateOfIssue;

	private PrivilegerInfoBuilder() {
	}

	public PrivilegerInfoBuilder withType(final String type) {
	    this.type = MyStrings.requireNonEmpty(type, "type");
	    return this;
	}

	public PrivilegerInfoBuilder withCertificateNumber(final String certificateNumber) {
	    this.certificateNumber = MyStrings.requireNonEmpty(certificateNumber, "certificateNumber");
	    return this;
	}

	public PrivilegerInfoBuilder withCertificateDateOfIssue(final LocalDate certificateDateOfIssue) {
	    this.certificateDateOfIssue = MyObjects.requireNonNull(certificateDateOfIssue, "certificateDateOfIssue");
	    return this;
	}

	public PrivilegerInfo build() {
	    final PrivilegerInfo res = new PrivilegerInfo();
	    res.type = MyStrings.requireNonEmpty(type, "type");
	    res.certificateNumber = MyStrings.requireNonEmpty(certificateNumber, "certificateNumber");
	    res.certificateDateOfIssue = MyObjects.requireNonNull(certificateDateOfIssue, "certificateDateOfIssue");
	    return res;
	}

	public void buildTo(final Consumer<PrivilegerInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private PrivilegerInfo() {
    }

    // type

    private String type;

    public String getType() {
	return type;
    }

    // certificateNumber

    private String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    // certificateDateOfIssue

    private LocalDate certificateDateOfIssue;

    public LocalDate getCertificateDateOfIssue() {
	return certificateDateOfIssue;
    }
}
