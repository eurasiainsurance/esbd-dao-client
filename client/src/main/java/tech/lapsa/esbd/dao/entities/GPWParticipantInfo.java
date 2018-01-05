package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(53)
public class GPWParticipantInfo extends Domain {

    private static final long serialVersionUID = 1L;

    String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    LocalDate certificateDateOfIssue;

    public LocalDate getCertificateDateOfIssue() {
	return certificateDateOfIssue;
    }
}
