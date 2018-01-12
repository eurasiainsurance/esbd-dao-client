package tech.lapsa.esbd.dao.entities;

import java.util.Optional;
import java.util.function.Consumer;

import com.lapsa.insurance.elements.SubjectType;
import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

/**
 * Абстрактный класс для представления лица
 *
 * @author vadim.isaev
 *
 */
public abstract class SubjectEntity extends Domain {

    private static final long serialVersionUID = 1L;

    protected abstract static class SubjectEntityBuilder<T extends SubjectEntity, THIS> {

	private Integer id;
	private OriginInfo origin;
	private ContactInfo contact;
	private String taxPayerNumber;
	private String comments;
	private boolean resident;
	private TaxpayerNumber idNumber;
	private KZEconomicSector economicsSector;

	protected abstract THIS _this();

	protected SubjectEntityBuilder() {
	}

	public THIS withId(Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return _this();
	}

	public THIS withOrigin(OriginInfo origin) {
	    this.origin = origin;
	    return _this();
	}

	public THIS withContact(ContactInfo contact) {
	    this.contact = contact;
	    return _this();
	}

	public THIS withTaxPayerNumber(String taxPayerNumber) {
	    this.taxPayerNumber = taxPayerNumber;
	    return _this();
	}

	public THIS withComments(String comments) {
	    this.comments = comments;
	    return _this();
	}

	public THIS withResident(boolean resident) {
	    this.resident = resident;
	    return _this();
	}

	public THIS withIdNumber(TaxpayerNumber idNumber) {
	    this.idNumber = idNumber;
	    return _this();
	}

	public THIS withIdNumber(Optional<TaxpayerNumber> optIdNumber) {
	    if (MyObjects.requireNonNull(optIdNumber, "optIdNumber").isPresent())
		return withIdNumber(optIdNumber.get());
	    this.idNumber = null;
	    return _this();
	}

	public THIS withEconomicsSector(KZEconomicSector economicsSector) {
	    this.economicsSector = economicsSector;
	    return _this();
	}

	public THIS withEconomicsSector(Optional<KZEconomicSector> optEconomicsSector) {
	    if (MyObjects.requireNonNull(optEconomicsSector, "optEconomicsSector").isPresent())
		return withEconomicsSector(optEconomicsSector.get());
	    this.economicsSector = null;
	    return _this();
	}

	public void buildTo(final Consumer<T> consumer) {
	    consumer.accept(build());
	}

	public abstract T build() throws IllegalArgumentException;

	protected void superFill(SubjectEntity res) {
	    res.id = MyNumbers.requirePositive(id, "id");
	    res.origin = origin;
	    res.contact = contact;
	    res.taxPayerNumber = taxPayerNumber;
	    res.comments = comments;
	    res.resident = resident;
	    res.idNumber = idNumber;
	    res.economicsSector = economicsSector;
	}
    }

    protected SubjectEntity() {
    }

    public abstract SubjectType getSubjectType();

    private Integer id;

    public Integer getId() {
	return id;
    }

    private OriginInfo origin;

    public OriginInfo getOrigin() {
	return origin;
    }

    private ContactInfo contact;

    public ContactInfo getContact() {
	return contact;
    }

    private String taxPayerNumber;

    public String getTaxPayerNumber() {
	return taxPayerNumber;
    }

    private String comments;

    public String getComments() {
	return comments;
    }

    private boolean resident;

    public boolean isResident() {
	return resident;
    }

    private TaxpayerNumber idNumber;

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    private KZEconomicSector economicsSector;

    public KZEconomicSector getEconomicsSector() {
	return economicsSector;
    }
}
