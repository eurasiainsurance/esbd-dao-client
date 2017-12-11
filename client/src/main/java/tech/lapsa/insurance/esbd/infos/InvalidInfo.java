package tech.lapsa.insurance.esbd.infos;

import java.time.LocalDate;

import tech.lapsa.insurance.esbd.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(61)
public class InvalidInfo extends Domain {

    private static final long serialVersionUID = 1L;

    private String certificateNumber;
    private LocalDate certificateValidFrom;
    private LocalDate certificateValidTill;

    // GENERATED

    public String getCertificateNumber() {
	return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public LocalDate getCertificateValidFrom() {
	return certificateValidFrom;
    }

    public void setCertificateValidFrom(LocalDate certificateValidFrom) {
	this.certificateValidFrom = certificateValidFrom;
    }

    public LocalDate getCertificateValidTill() {
	return certificateValidTill;
    }

    public void setCertificateValidTill(LocalDate certificateValidTill) {
	this.certificateValidTill = certificateValidTill;
    }

}
