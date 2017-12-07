package tech.lapsa.insurance.esbd.infos;

import java.time.LocalDate;

import tech.lapsa.patterns.domain.Pojo;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(79)
public class PrivilegerInfo extends Pojo {

    private static final long serialVersionUID = 1L;

    private String type;
    private String certificateNumber;
    private LocalDate certificateDateOfIssue;

    // GENERATED

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

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
