package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(47)
public class DriverLicenseInfo extends Domain {

    private static final long serialVersionUID = 1L;

    String number;

    public String getNumber() {
	return number;
    }

    LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }
}
