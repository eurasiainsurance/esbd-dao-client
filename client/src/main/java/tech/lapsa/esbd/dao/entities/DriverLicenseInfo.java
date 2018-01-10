package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(47)
public class DriverLicenseInfo extends Domain {

    private static final long serialVersionUID = 1L;

    public static final DriverLicenseInfoBuilder builder() {
	return new DriverLicenseInfoBuilder();
    }

    public static final class DriverLicenseInfoBuilder {

	private String number;
	private LocalDate dateOfIssue;

	private DriverLicenseInfoBuilder() {
	}

	public DriverLicenseInfoBuilder withNumber(final String number) {
	    this.number = number;
	    return this;
	}

	public DriverLicenseInfoBuilder withDateOfIssue(final LocalDate dateOfIssue) {
	    this.dateOfIssue = dateOfIssue;
	    return this;
	}

	public DriverLicenseInfo build() {
	    return new DriverLicenseInfo(number, dateOfIssue);
	}

	public void buildTo(final Consumer<DriverLicenseInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private DriverLicenseInfo(final String number, final LocalDate dateOfIssue) {
	this.number = number;
	this.dateOfIssue = dateOfIssue;
    }

    private final String number;

    public String getNumber() {
	return number;
    }

    private final LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }
}
