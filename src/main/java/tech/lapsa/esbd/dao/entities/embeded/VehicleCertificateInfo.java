package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.kz.vehicle.VehicleRegNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(89)
public class VehicleCertificateInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final VehicleCertificateInfoBuilder builder() {
	return new VehicleCertificateInfoBuilder();
    }

    public static final class VehicleCertificateInfoBuilder {

	private VehicleRegNumber registrationNumber;
	private String certificateNumber;
	private LocalDate dateOfIssue;
	private KZArea registrationRegion;
	private Boolean registrationMajorCity;

	private VehicleCertificateInfoBuilder() {
	}

	public VehicleCertificateInfoBuilder withRegistrationNumber(final VehicleRegNumber registrationNumber) {
	    this.registrationNumber = MyObjects.requireNonNull(registrationNumber, "registrationNumber");
	    return this;
	}

	public VehicleCertificateInfoBuilder withCertificateNumber(final String certificateNumber) {
	    this.certificateNumber = MyStrings.requireNonEmpty(certificateNumber, "certificateNumber");
	    return this;
	}

	public VehicleCertificateInfoBuilder withDateOfIssue(final LocalDate dateOfIssue) {
	    this.dateOfIssue = MyObjects.requireNonNull(dateOfIssue, "dateOfIssue");
	    return this;
	}

	public VehicleCertificateInfoBuilder withRegistrationRegion(final KZArea registrationRegion) {
	    this.registrationRegion = MyObjects.requireNonNull(registrationRegion, "registrationRegion");
	    return this;
	}

	public VehicleCertificateInfoBuilder withRegistrationMajorCity(final Boolean registrationMajorCity) {
	    this.registrationMajorCity = MyObjects.requireNonNull(registrationMajorCity, "registrationMajorCity");
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

    private VehicleCertificateInfo(final VehicleRegNumber registrationNumber,
	    final String certificateNumber,
	    final LocalDate dateOfIssue,
	    final KZArea registrationRegion,
	    final Boolean registrationMajorCity) {
	this.registrationNumber = registrationNumber;
	this.certificateNumber = certificateNumber;
	this.dateOfIssue = dateOfIssue;
	this.registrationRegion = registrationRegion;
	this.registrationMajorCity = registrationMajorCity;
    }

    // registrationNumber

    private final VehicleRegNumber registrationNumber;

    public VehicleRegNumber getRegistrationNumber() {
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

    private final Boolean registrationMajorCity;

    boolean isRegistrationMajorCity() {
	return registrationMajorCity;
    }
}
