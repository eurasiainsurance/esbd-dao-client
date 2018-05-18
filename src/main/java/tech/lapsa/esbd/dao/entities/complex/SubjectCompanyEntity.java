package tech.lapsa.esbd.dao.entities.complex;

import java.util.Optional;

import com.lapsa.insurance.elements.SubjectType;

import tech.lapsa.esbd.dao.entities.dict.CompanyActivityKindEntity;
import tech.lapsa.java.commons.function.MyObjects;
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
	    this.companyName = companyName;
	    return this;
	}

	public SubjectCompanyEntityBuilder withHeadName(final String headName) {
	    this.headName = headName;
	    return this;
	}

	public SubjectCompanyEntityBuilder withAccountantName(final String accountantName) {
	    this.accountantName = accountantName;
	    return this;
	}

	public SubjectCompanyEntityBuilder withCompanyActivityKind(
		final CompanyActivityKindEntity companyActivityKind) {
	    this.companyActivityKind = companyActivityKind;
	    return this;
	}

	public SubjectCompanyEntityBuilder withCompanyActivityKind(
		final Optional<CompanyActivityKindEntity> optCompanyActivityKind) {
	    if (MyObjects.requireNonNull(optCompanyActivityKind, "optCompanyActivityKind").isPresent())
		return withCompanyActivityKind(optCompanyActivityKind.get());
	    companyActivityKind = null;
	    return this;
	}

	@Override
	protected SubjectCompanyEntityBuilder _this() {
	    return this;
	}

	@Override
	public SubjectCompanyEntity build() throws IllegalArgumentException {
	    final SubjectCompanyEntity res = new SubjectCompanyEntity();
	    superFill(res);
	    res.companyName = companyName;
	    res.headName = headName;
	    res.accountantName = accountantName;
	    res.companyActivityKind = companyActivityKind;
	    return res;
	}
    }

    private SubjectCompanyEntity() {
    }

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.COMPANY;
    }

    // companyName

    private String companyName;

    public String getCompanyName() {
	return companyName;
    }

    // headName

    private String headName;

    public String getHeadName() {
	return headName;
    }

    // accountantName

    private String accountantName;

    public String getAccountantName() {
	return accountantName;
    }

    // companyActivityKind

    private CompanyActivityKindEntity companyActivityKind;

    public CompanyActivityKindEntity getCompanyActivityKind() {
	return companyActivityKind;
    }
}
