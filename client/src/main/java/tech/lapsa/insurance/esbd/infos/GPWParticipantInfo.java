package tech.lapsa.insurance.esbd.infos;

import java.time.LocalDate;

import tech.lapsa.insurance.esbd.Domain;
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

    public void setCertificateNumber(String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public LocalDate getCertificateDateOfIssue() {
	return certificateDateOfIssue;
    }

    public void setCertificateDateOfIssue(LocalDate certificateDateOfIssue) {
	this.certificateDateOfIssue = certificateDateOfIssue;
    }

}
