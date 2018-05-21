package tech.lapsa.esbd.dao.entities.complex;

import java.util.Optional;
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

/**
 * Абстрактный класс для представления лица
 *
 * @author vadim.isaev
 *
 */
public abstract class SubjectEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public abstract static class SubjectEntityBuilder<T extends SubjectEntity, THIS> {

	private Integer id;
	private OriginInfo origin;
	private ContactInfo contact;
	private String taxPayerNumber;
	private String comments;
	private Boolean resident;
	private TaxpayerNumber idNumber;
	private KZEconomicSector economicsSector;

	protected abstract THIS _this();

	protected SubjectEntityBuilder() {
	}

	public THIS withId(final Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return _this();
	}

	public THIS withOrigin(final OriginInfo origin) {
	    this.origin = origin;
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

	public THIS withIdNumber(final Optional<TaxpayerNumber> optIdNumber) {
	    if (MyObjects.requireNonNull(optIdNumber, "optIdNumber").isPresent())
		return withIdNumber(optIdNumber.get());
	    this.idNumber = null;
	    return _this();
	}

	public THIS withEconomicsSector(final KZEconomicSector economicsSector) {
	    this.economicsSector = economicsSector;
	    return _this();
	}

	public THIS withEconomicsSector(final Optional<KZEconomicSector> optEconomicsSector) {
	    if (MyObjects.requireNonNull(optEconomicsSector, "optEconomicsSector").isPresent())
		return withEconomicsSector(optEconomicsSector.get());
	    this.economicsSector = null;
	    return _this();
	}

	public void buildTo(final Consumer<T> consumer) {
	    consumer.accept(build());
	}

	public abstract T build() throws IllegalArgumentException;

	protected void superFill(final SubjectEntity res) {
	    res.id = MyNumbers.requirePositive(id, "id");
	    res.origin = origin;
	    res.contact = contact;
	    res.taxPayerNumber = taxPayerNumber;
	    res.comments = comments;
	    res.resident = MyObjects.requireNonNull(resident, "resident");
	    res.idNumber =  MyObjects.requireNonNull(idNumber, "idNumber");
	    res.economicsSector = economicsSector;
	}
    }

    protected SubjectEntity() {
    }

    public abstract SubjectType getSubjectType();

    // id

    private Integer id;

    public Integer getId() {
	return id;
    }

    // origin

    private OriginInfo origin;

    public OriginInfo getOrigin() {
	return origin;
    }

    // contact

    private ContactInfo contact;

    public ContactInfo getContact() {
	return contact;
    }

    // taxPayerNumber

    private String taxPayerNumber;

    public String getTaxPayerNumber() {
	return taxPayerNumber;
    }

    // comments

    private String comments;

    public String getComments() {
	return comments;
    }

    // resident

    private Boolean resident;

    public Boolean isResident() {
	return resident;
    }

    // idNumber

    private TaxpayerNumber idNumber;

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    // economicsSector

    private KZEconomicSector economicsSector;

    public KZEconomicSector getEconomicsSector() {
	return economicsSector;
    }
}
