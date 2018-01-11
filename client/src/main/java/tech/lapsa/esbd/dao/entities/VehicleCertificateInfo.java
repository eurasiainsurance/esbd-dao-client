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

	public VehicleCertificateInfoBuilder withRegistrationNumber(final String registrationNumber) {
	    this.registrationNumber = registrationNumber;
	    return this;
	}

	public VehicleCertificateInfoBuilder withCertificateNumber(final String certificateNumber) {
	    this.certificateNumber = certificateNumber;
	    return this;
	}

	public VehicleCertificateInfoBuilder withDateOfIssue(final LocalDate dateOfIssue) {
	    this.dateOfIssue = dateOfIssue;
	    return this;
	}

	public VehicleCertificateInfoBuilder withRegistrationRegion(final KZArea registrationRegion) {
	    this.registrationRegion = registrationRegion;
	    return this;
	}

	public VehicleCertificateInfoBuilder withRegistrationMajorCity(final boolean registrationMajorCity) {
	    this.registrationMajorCity = registrationMajorCity;
	    return this;
	}

	public VehicleCertificateInfo build() {
	    final VehicleCertificateInfo res = new VehicleCertificateInfo();
	    res.registrationNumber = registrationNumber;
	    res.certificateNumber = certificateNumber;
	    res.dateOfIssue = dateOfIssue;
	    res.registrationRegion = registrationRegion;
	    res.registrationMajorCity = registrationMajorCity;
	    return res;
	}

	public void buildTo(final Consumer<VehicleCertificateInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private VehicleCertificateInfo() {
    }

    // registrationNumber

    private String registrationNumber;

    public String getRegistrationNumber() {
	return registrationNumber;
    }

    // certificateNumber

    private String certificateNumber;

    public String getCertificateNumber() {
	return certificateNumber;
    }

    // dateOfIssue

    private LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // registrationRegion

    private KZArea registrationRegion;

    public KZArea getRegistrationRegion() {
	return registrationRegion;
    }

    // registrationMajorCity

    private boolean registrationMajorCity;

    boolean isRegistrationMajorCity() {
	return registrationMajorCity;
    }
}
