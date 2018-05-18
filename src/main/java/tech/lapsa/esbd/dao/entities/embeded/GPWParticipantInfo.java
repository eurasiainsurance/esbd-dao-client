package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(53)
public class GPWParticipantInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final GPWParticipantInfoBuilder builder() {
	return new GPWParticipantInfoBuilder();
    }

    public static final class GPWParticipantInfoBuilder {

	private String certificateNumber;
	private LocalDate certificateDateOfIssue;

	private GPWParticipantInfoBuilder() {
	}

	public GPWParticipantInfoBuilder withCertificateNumber(final String certificateNumber) {
	    this.certificateNumber = certificateNumber;
	    return this;
	}

	public GPWParticipantInfoBuilder withCertificateDateOfIssue(final LocalDate dateOfIssue) {
	    certificateDateOfIssue = dateOfIssue;
	    return this;
	}

	public GPWParticipantInfo build() {
	    final GPWParticipantInfo res = new GPWParticipantInfo();
	    res.certificateNumber = certificateNumber;
	    res.certificateDateOfIssue = certificateDateOfIssue;
	    return res;
	}

	public void buildTo(final Consumer<GPWParticipantInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private GPWParticipantInfo() {
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
