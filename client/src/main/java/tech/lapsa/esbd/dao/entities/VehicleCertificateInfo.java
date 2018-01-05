package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(89)
public class VehicleCertificateInfo extends Domain {

    private static final long serialVersionUID = 1L;

    String registrationNumber;

    public String getRegistrationNumber() {
	return registrationNumber;
    }

    String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    int registrationRegionId;
    KZArea registrationRegion;

    public KZArea getRegistrationRegion() {
	return registrationRegion;
    }

    void setRegistrationRegion(KZArea registrationRegion) {
	this.registrationRegion = registrationRegion;
    }

    boolean registrationMajorCity;

    boolean isRegistrationMajorCity() {
        return registrationMajorCity;
    }
}
