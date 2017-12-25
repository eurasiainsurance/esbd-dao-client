package tech.lapsa.esbd.dao.infos;

import java.util.Calendar;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(89)
public class VehicleCertificateInfo extends Domain {

    private static final long serialVersionUID = 1L;

    private String registrationNumber;

    private String certificateNumber;

    private Calendar dateOfIssue;

    private KZArea registrationRegion;

    private boolean majorCity = false;

    // GENERATED

    public String getRegistrationNumber() {
	return registrationNumber;
    }

    public void setRegistrationNumber(final String registrationNumber) {
	this.registrationNumber = registrationNumber;
    }

    public String getCertificateNumber() {
	return certificateNumber;
    }

    public void setCertificateNumber(final String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public Calendar getDateOfIssue() {
	return dateOfIssue;
    }

    public void setDateOfIssue(final Calendar dateOfIssue) {
	this.dateOfIssue = dateOfIssue;
    }

    public KZArea getRegistrationRegion() {
	return registrationRegion;
    }

    public void setRegistrationRegion(final KZArea registrationRegion) {
	this.registrationRegion = registrationRegion;
    }

    public boolean isMajorCity() {
	return majorCity;
    }

    public void setMajorCity(final boolean majorCity) {
	this.majorCity = majorCity;
    }

}
