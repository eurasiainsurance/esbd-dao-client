package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(79)
public class PrivilegerInfo extends Domain {

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
	    this.type = type;
	    return this;
	}

	public PrivilegerInfoBuilder withCertificateNumber(final String certificateNumber) {
	    this.certificateNumber = certificateNumber;
	    return this;
	}

	public PrivilegerInfoBuilder withCertificateDateOfIssue(final LocalDate certificateDateOfIssue) {
	    this.certificateDateOfIssue = certificateDateOfIssue;
	    return this;
	}

	public PrivilegerInfo build() {
	    return new PrivilegerInfo(type, certificateNumber, certificateDateOfIssue);
	}

	public void buildTo(final Consumer<PrivilegerInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private PrivilegerInfo(final String type, final String certificateNumber, final LocalDate certificateDateOfIssue) {
	this.type = type;
	this.certificateNumber = certificateNumber;
	this.certificateDateOfIssue = certificateDateOfIssue;
    }

    private final String type;

    public String getType() {
	return type;
    }

    private final String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    private final LocalDate certificateDateOfIssue;

    public LocalDate getCertificateDateOfIssue() {
	return certificateDateOfIssue;
    }
}
