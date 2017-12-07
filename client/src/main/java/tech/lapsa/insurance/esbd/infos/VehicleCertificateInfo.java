package tech.lapsa.insurance.esbd.infos;

import java.util.Calendar;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.patterns.domain.Pojo;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(89)
public class VehicleCertificateInfo extends Pojo {

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

    public void setRegistrationNumber(String registrationNumber) {
	this.registrationNumber = registrationNumber;
    }

    public String getCertificateNumber() {
	return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public Calendar getDateOfIssue() {
	return dateOfIssue;
    }

    public void setDateOfIssue(Calendar dateOfIssue) {
	this.dateOfIssue = dateOfIssue;
    }

    public KZArea getRegistrationRegion() {
	return registrationRegion;
    }

    public void setRegistrationRegion(KZArea registrationRegion) {
	this.registrationRegion = registrationRegion;
    }

    public boolean isMajorCity() {
	return majorCity;
    }

    public void setMajorCity(boolean majorCity) {
	this.majorCity = majorCity;
    }

}
