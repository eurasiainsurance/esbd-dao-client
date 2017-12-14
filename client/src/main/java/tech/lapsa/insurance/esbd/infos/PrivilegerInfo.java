package tech.lapsa.insurance.esbd.infos;

import java.time.LocalDate;

import tech.lapsa.insurance.esbd.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(79)
public class PrivilegerInfo extends Domain {

    private static final long serialVersionUID = 1L;

    private String type;
    private String certificateNumber;
    private LocalDate certificateDateOfIssue;

    // GENERATED

    public String getType() {
	return type;
    }

    public void setType(final String type) {
	this.type = type;
    }

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
