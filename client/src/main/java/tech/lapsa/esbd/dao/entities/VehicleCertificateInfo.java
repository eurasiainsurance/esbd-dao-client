package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(89)
public class VehicleCertificateInfo extends Domain {

    private static final long serialVersionUID = 1L;

    public static final VehicleCertificateInfoBuilder builder() {
	return new VehicleCertificateInfoBuilder();
    }

    public static final class VehicleCertificateInfoBuilder {

	private String registrationNumber;
	private String certificateNumber;
	private LocalDate dateOfIssue;
	private KZArea registrationRegion;
	private boolean registrationMajorCity;

	private VehicleCertificateInfoBuilder() {
	}

	public VehicleCertificateInfoBuilder withRegistrationNumber(String registrationNumber) {
	    this.registrationNumber = registrationNumber;
	    return this;
	}

	public VehicleCertificateInfoBuilder withCertificateNumber(String certificateNumber) {
	    this.certificateNumber = certificateNumber;
	    return this;
	}

	public VehicleCertificateInfoBuilder withDateOfIssue(LocalDate dateOfIssue) {
	    this.dateOfIssue = dateOfIssue;
	    return this;
	}

	public VehicleCertificateInfoBuilder withRegistrationRegion(KZArea registrationRegion) {
	    this.registrationRegion = registrationRegion;
	    return this;
	}

	public VehicleCertificateInfoBuilder withRegistrationMajorCity(boolean registrationMajorCity) {
	    this.registrationMajorCity = registrationMajorCity;
	    return this;
	}

	public VehicleCertificateInfo build() {
	    return new VehicleCertificateInfo(registrationNumber,
		    certificateNumber,
		    dateOfIssue,
		    registrationRegion,
		    registrationMajorCity);
	}

	public void buildTo(final Consumer<VehicleCertificateInfo> consumer) {
	    consumer.accept(build());
	}

    }

    private VehicleCertificateInfo(final String registrationNumber,
	    final String certificateNumber,
	    final LocalDate dateOfIssue,
	    final KZArea registrationRegion,
	    final boolean registrationMajorCity) {
	this.registrationNumber = registrationNumber;
	this.certificateNumber = certificateNumber;
	this.dateOfIssue = dateOfIssue;
	this.registrationRegion = registrationRegion;
	this.registrationMajorCity = registrationMajorCity;
    }

    // registrationNumber

    private final String registrationNumber;

    public String getRegistrationNumber() {
	return registrationNumber;
    }

    // certificateNumber

    private final String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    // dateOfIssue

    private final LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // registrationRegion

    private final KZArea registrationRegion;

    public KZArea getRegistrationRegion() {
	return registrationRegion;
    }

    // registrationMajorCity

    private final boolean registrationMajorCity;

    boolean isRegistrationMajorCity() {
	return registrationMajorCity;
    }
}
