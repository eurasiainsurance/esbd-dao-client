package tech.lapsa.esbd.dao.entities;

import com.lapsa.insurance.elements.SubjectType;

import tech.lapsa.esbd.dao.dict.CompanyActivityKindEntity;
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

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.COMPANY;
    }

    // Juridical_Person_Name s:string Наименование (для юр. лица)
    String companyName;

    public String getCompanyName() {
	return companyName;
    }

    // MAIN_CHIEF s:string Первый руководитель
    String headName;

    public String getHeadName() {
	return headName;
    }

    // MAIN_ACCOUNTANT s:string Главный бухгалтер
    String accountantName;

    public String getAccountantName() {
	return accountantName;
    }

    // ACTIVITY_KIND_ID s:int Вид деятельности (справочник ACTIVITY_KINDS)
    int _companyActivityKind;
    CompanyActivityKindEntity companyActivityKind;

    public CompanyActivityKindEntity getCompanyActivityKind() {
	return companyActivityKind;
    }

    void setCompanyActivityKind(final CompanyActivityKindEntity companyActivityKind) {
	this.companyActivityKind = companyActivityKind;
    }
}
