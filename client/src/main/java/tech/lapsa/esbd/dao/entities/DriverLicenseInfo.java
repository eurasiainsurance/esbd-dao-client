package tech.lapsa.esbd.dao.infos;

import java.time.LocalDate;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(47)
public class DriverLicenseInfo extends Domain {

    private static final long serialVersionUID = 1L;

    private String number;
    private LocalDate dateOfIssue;

    // GENERATED

    public String getNumber() {
	return number;
    }

    public void setNumber(final String number) {
	this.number = number;
    }

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    public void setDateOfIssue(final LocalDate dateOfIssue) {
	this.dateOfIssue = dateOfIssue;
    }

}
