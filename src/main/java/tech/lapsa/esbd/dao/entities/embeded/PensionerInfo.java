package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(71)
public class PensionerInfo extends AEntity {

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
	    final PensionerInfo res = new PensionerInfo();
	    res.certificateNumber = certificateNumber;
	    res.certiticateDateOfIssue = certiticateDateOfIssue;
	    return res;
	}

	public void buildTo(final Consumer<PensionerInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private PensionerInfo() {
    }

    // certificateNumber

    private String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    // certiticateDateOfIssue

    private LocalDate certiticateDateOfIssue;

    public LocalDate getCertiticateDateOfIssue() {
	return certiticateDateOfIssue;
    }
}
