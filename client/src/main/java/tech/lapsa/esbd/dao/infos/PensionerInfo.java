package tech.lapsa.insurance.esbd.infos;

import java.time.LocalDate;

import tech.lapsa.insurance.esbd.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(71)
public class PensionerInfo extends Domain {

    private static final long serialVersionUID = 1L;

    private String certificateNumber;
    private LocalDate certiticateDateOfIssue;

    // GENERATED

    public String getCertificateNumber() {
	return certificateNumber;
    }

    public void setCertificateNumber(final String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public LocalDate getCertiticateDateOfIssue() {
	return certiticateDateOfIssue;
    }

    public void setCertiticateDateOfIssue(final LocalDate certiticateDateOfIssue) {
	this.certiticateDateOfIssue = certiticateDateOfIssue;
    }

}
