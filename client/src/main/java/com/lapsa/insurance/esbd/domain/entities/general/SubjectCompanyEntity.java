package com.lapsa.insurance.esbd.domain.entities.general;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.lapsa.insurance.elements.SubjectType;

/**
 * Класс для представления субъекта - юридического лица (компания)
 * 
 * @author vadim.isaev
 *
 */
public class SubjectCompanyEntity extends SubjectEntity {

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    // Juridical_Person_Name s:string Наименование (для юр. лица)
    private String companyName;

    // MAIN_CHIEF s:string Первый руководитель
    private String headName;

    // MAIN_ACCOUNTANT s:string Главный бухгалтер
    private String accountantName;

    // ACTIVITY_KIND_ID s:int Вид деятельности (справочник ACTIVITY_KINDS)
    private long companyActivityKindId;
    private CompanyActivityKindEntity companyActivityKind;

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.COMPANY;
    }

    // GENERATED

    public String getCompanyName() {
	return companyName;
    }

    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }

    public String getHeadName() {
	return headName;
    }

    public void setHeadName(String headName) {
	this.headName = headName;
    }

    public String getAccountantName() {
	return accountantName;
    }

    public void setAccountantName(String accountantName) {
	this.accountantName = accountantName;
    }

    public long getCompanyActivityKindId() {
	return companyActivityKindId;
    }

    public void setCompanyActivityKindId(long companyActivityKindId) {
	this.companyActivityKindId = companyActivityKindId;
    }

    public CompanyActivityKindEntity getCompanyActivityKind() {
	return companyActivityKind;
    }

    public void setCompanyActivityKind(CompanyActivityKindEntity companyActivityKind) {
	this.companyActivityKind = companyActivityKind;
    }
}
