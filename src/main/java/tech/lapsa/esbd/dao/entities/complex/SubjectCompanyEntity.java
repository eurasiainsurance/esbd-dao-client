package tech.lapsa.esbd.dao.entities.complex;

import com.lapsa.insurance.elements.SubjectType;
import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.entities.dict.CompanyActivityKindEntity;
import tech.lapsa.esbd.dao.entities.embeded.ContactInfo;
import tech.lapsa.esbd.dao.entities.embeded.OriginInfo;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для представления субъекта - юридического лица (компания)
 *
 * @author vadim.isaev
 *
 */
@HashCodePrime(19)
public class SubjectCompanyEntity extends SubjectEntity {

    private static final long serialVersionUID = 1L;

    public static final SubjectCompanyEntityBuilder builder() {
	return new SubjectCompanyEntityBuilder();
    }

    public static final class SubjectCompanyEntityBuilder
	    extends SubjectEntityBuilder<SubjectCompanyEntity, SubjectCompanyEntityBuilder> {

	private String companyName;
	private String headName;
	private String accountantName;
	private CompanyActivityKindEntity companyActivityKind;

	private SubjectCompanyEntityBuilder() {
	}

	public SubjectCompanyEntityBuilder withCompanyName(final String companyName) {
	    this.companyName = MyStrings.requireNonEmpty(companyName, "companyName");
	    return this;
	}

	public SubjectCompanyEntityBuilder withHeadName(final String headName) {
	    this.headName = MyStrings.requireNonEmpty(headName, "headName");
	    return this;
	}

	public SubjectCompanyEntityBuilder withAccountantName(final String accountantName) {
	    this.accountantName = MyStrings.requireNonEmpty(accountantName, "accountantName");
	    return this;
	}

	public SubjectCompanyEntityBuilder withCompanyActivityKind(
		final CompanyActivityKindEntity companyActivityKind) {
	    this.companyActivityKind = MyObjects.requireNonNull(companyActivityKind, "companyActivityKind");
	    return this;
	}

	@Override
	protected SubjectCompanyEntityBuilder _this() {
	    return this;
	}

	@Override
	public SubjectCompanyEntity build() throws IllegalArgumentException {
	    return new SubjectCompanyEntity(id,
		    origin,
		    contact,
		    taxPayerNumber,
		    comments,
		    resident,
		    idNumber,
		    economicsSector,
		    companyName,
		    headName,
		    accountantName,
		    companyActivityKind);
	}
    }

    // constructor

    private SubjectCompanyEntity(Integer id,
	    final OriginInfo origin,
	    final ContactInfo contact,
	    final String taxPayerNumber,
	    final String comments,
	    final Boolean resident,
	    final TaxpayerNumber idNumber,
	    final KZEconomicSector economicsSector,
	    final String companyName,
	    final String headName,
	    final String accountantName,
	    final CompanyActivityKindEntity companyActivityKind) {
	super(id, origin, contact, taxPayerNumber, comments, resident, idNumber, economicsSector);
	this.companyName = companyName;
	this.headName = headName;
	this.accountantName = accountantName;
	this.companyActivityKind = companyActivityKind;
    }

    // subjectType

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.COMPANY;
    }

    // companyName

    private final String companyName;

    public String getCompanyName() {
	return companyName;
    }

    // headName

    private final String headName;

    public String getHeadName() {
	return headName;
    }

    // accountantName

    private final String accountantName;

    public String getAccountantName() {
	return accountantName;
    }

    // companyActivityKind

    private final CompanyActivityKindEntity companyActivityKind;

    public CompanyActivityKindEntity getCompanyActivityKind() {
	return companyActivityKind;
    }
}
