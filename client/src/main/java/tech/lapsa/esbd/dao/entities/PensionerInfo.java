package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(71)
public class PensionerInfo extends Domain {

    private static final long serialVersionUID = 1L;

    public static final PensionerInfoBuilder builder() {
	return new PensionerInfoBuilder();
    }

    public static final class PensionerInfoBuilder {

	private String certificateNumber;
	private LocalDate certiticateDateOfIssue;

	private PensionerInfoBuilder() {
	}

	public PensionerInfoBuilder withCertificateNumber(final String certificateNumber) {
	    this.certificateNumber = certificateNumber;
	    return this;
	}

	public PensionerInfoBuilder withCertiticateDateOfIssue(final LocalDate certiticateDateOfIssue) {
	    this.certiticateDateOfIssue = certiticateDateOfIssue;
	    return this;
	}

	public PensionerInfo build() {
	    return new PensionerInfo(certificateNumber, certiticateDateOfIssue);
	}

	public void buildTo(final Consumer<PensionerInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private PensionerInfo(final String certificateNumber, final LocalDate certiticateDateOfIssue) {
	this.certificateNumber = certificateNumber;
	this.certiticateDateOfIssue = certiticateDateOfIssue;
    }

    private final String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    private final LocalDate certiticateDateOfIssue;

    public LocalDate getCertiticateDateOfIssue() {
	return certiticateDateOfIssue;
    }
}
