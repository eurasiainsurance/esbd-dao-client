package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(61)
public class HandicappedInfo extends Domain {

    private static final long serialVersionUID = 1L;

    String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    LocalDate certificateValidFrom;

    public LocalDate getCertificateValidFrom() {
	return certificateValidFrom;
    }

    LocalDate certificateValidTill;

    public LocalDate getCertificateValidTill() {
	return certificateValidTill;
    }
}
