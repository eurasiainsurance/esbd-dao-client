package tech.lapsa.esbd.dao.infos;

import java.time.LocalDate;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(53)
public class GPWParticipantInfo extends Domain {

    private static final long serialVersionUID = 1L;

    private String certificateNumber;
    private LocalDate certificateDateOfIssue;

    // GENERATED

    public String getCertificateNumber() {
	return certificateNumber;
    }

    public void setCertificateNumber(final String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public LocalDate getCertificateDateOfIssue() {
	return certificateDateOfIssue;
    }

    public void setCertificateDateOfIssue(final LocalDate certificateDateOfIssue) {
	this.certificateDateOfIssue = certificateDateOfIssue;
    }

}
