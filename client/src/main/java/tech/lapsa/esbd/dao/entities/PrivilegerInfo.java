package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(79)
public class PrivilegerInfo extends Domain {

    private static final long serialVersionUID = 1L;

    String type;

    public String getType() {
	return type;
    }

    String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    LocalDate certificateDateOfIssue;

    public LocalDate getCertificateDateOfIssue() {
	return certificateDateOfIssue;
    }
}
