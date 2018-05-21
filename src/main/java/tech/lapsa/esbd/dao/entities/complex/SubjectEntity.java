package tech.lapsa.esbd.dao.entities.complex;

import java.util.function.Consumer;

import com.lapsa.insurance.elements.SubjectType;
import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.esbd.dao.entities.embeded.ContactInfo;
import tech.lapsa.esbd.dao.entities.embeded.OriginInfo;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public abstract class SubjectEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public abstract static class SubjectEntityBuilder<T extends SubjectEntity, THIS> {

	protected Integer id;
	protected OriginInfo origin;
	protected ContactInfo contact;
	protected String taxPayerNumber;
	protected String comments;
	protected Boolean resident;
	protected TaxpayerNumber idNumber;
	protected KZEconomicSector economicsSector;

	protected abstract THIS _this();

	protected SubjectEntityBuilder() {
	}

	public THIS withId(final Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return _this();
	}

	public THIS withOrigin(final OriginInfo origin) {
	    this.origin = MyObjects.requireNonNull(origin, "origin");
	    return _this();
	}

	public THIS withContact(final ContactInfo contact) {
	    this.contact = MyObjects.requireNonNull(contact, "contact");
	    return _this();
	}

	public THIS withTaxPayerNumber(final String taxPayerNumber) {
	    this.taxPayerNumber = MyStrings.requireNonEmpty(taxPayerNumber, "taxPayerNumber");
	    return _this();
	}

	public THIS withComments(final String comments) {
	    this.comments = MyStrings.requireNonEmpty(comments, "comments");
	    return _this();
	}

	public THIS withResident(final Boolean resident) {
	    this.resident = MyObjects.requireNonNull(resident, "resident");
	    return _this();
	}

	public THIS withIdNumber(final TaxpayerNumber idNumber) {
	    this.idNumber = MyObjects.requireNonNull(idNumber, "idNumber");
	    return _this();
	}

	public THIS withEconomicsSector(final KZEconomicSector economicsSector) {
	    this.economicsSector = MyObjects.requireNonNull(economicsSector, "economicsSector");
	    return _this();
	}

	public void buildTo(final Consumer<T> consumer) {
	    consumer.accept(build());
	}

	public abstract T build() throws IllegalArgumentException;
    }

    // constructor

    protected SubjectEntity(final Integer id,
	    final OriginInfo origin,
	    final ContactInfo contact,
	    final String taxPayerNumber,
	    final String comments,
	    final Boolean resident,
	    final TaxpayerNumber idNumber,
	    final KZEconomicSector economicsSector) {
	this.id = id;
	this.origin = origin;
	this.contact = contact;
	this.taxPayerNumber = taxPayerNumber;
	this.comments = comments;
	this.resident = resident;
	this.idNumber = idNumber;
	this.economicsSector = economicsSector;
    }

    // subjectType

    public abstract SubjectType getSubjectType();

    // id

    private final Integer id;

    public Integer getId() {
	return id;
    }

    // origin

    private final OriginInfo origin;

    public OriginInfo getOrigin() {
	return origin;
    }

    // contact

    private final ContactInfo contact;

    public ContactInfo getContact() {
	return contact;
    }

    // taxPayerNumber

    private final String taxPayerNumber;

    public String getTaxPayerNumber() {
	return taxPayerNumber;
    }

    // comments

    private final String comments;

    public String getComments() {
	return comments;
    }

    // resident

    private final Boolean resident;

    public Boolean isResident() {
	return resident;
    }

    // idNumber

    private final TaxpayerNumber idNumber;

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    // economicsSector

    private final KZEconomicSector economicsSector;

    public KZEconomicSector getEconomicsSector() {
	return economicsSector;
    }
}
