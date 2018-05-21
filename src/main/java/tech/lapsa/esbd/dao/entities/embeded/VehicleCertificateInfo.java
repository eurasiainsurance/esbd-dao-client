package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(89)
public class VehicleCertificateInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final VehicleCertificateInfoBuilder builder() {
	return new VehicleCertificateInfoBuilder();
    }

    public static final class VehicleCertificateInfoBuilder {

	private String registrationNumber;
	private String certificateNumber;
	private LocalDate dateOfIssue;
	private KZArea registrationRegion;
	private Boolean registrationMajorCity;

	private VehicleCertificateInfoBuilder() {
	}

	public VehicleCertificateInfoBuilder withRegistrationNumber(final String registrationNumber) {
	    this.registrationNumber = MyStrings.requireNonEmpty(registrationNumber, "registrationNumber");
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
	    final VehicleCertificateInfo res = new VehicleCertificateInfo();
	    res.registrationNumber = MyStrings.requireNonEmpty(registrationNumber, "registrationNumber");
	    res.certificateNumber = MyStrings.requireNonEmpty(certificateNumber, "certificateNumber");
	    res.dateOfIssue = MyObjects.requireNonNull(dateOfIssue, "dateOfIssue");
	    res.registrationRegion = MyObjects.requireNonNull(registrationRegion, "registrationRegion");
	    res.registrationMajorCity = MyObjects.requireNonNull(registrationMajorCity, "registrationMajorCity");
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

    private Boolean registrationMajorCity;

    boolean isRegistrationMajorCity() {
	return registrationMajorCity;
    }
}
