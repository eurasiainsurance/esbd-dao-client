package tech.lapsa.esbd.dao.infos;

import java.time.LocalDate;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(59)
public class IdentityCardInfo extends Domain {

    private static final long serialVersionUID = 1L;

    // DOCUMENT_GIVED_DATE s:string Дата выдачи документа
    private LocalDate dateOfIssue;

    // DOCUMENT_GIVED_BY s:string Документ выдан
    private String issuingAuthority;

    // DOCUMENT_NUMBER s:string Номер документа
    private String number;

    // DOCUMENT_TYPE_ID s:int Тип документа (справочник DOCUMENTS_TYPES)
    private IdentityCardType identityCardType;

    // GENERATED

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    public void setDateOfIssue(final LocalDate dateOfIssue) {
	this.dateOfIssue = dateOfIssue;
    }

    public String getIssuingAuthority() {
	return issuingAuthority;
    }

    public void setIssuingAuthority(final String issuingAuthority) {
	this.issuingAuthority = issuingAuthority;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(final String number) {
	this.number = number;
    }

    public IdentityCardType getIdentityCardType() {
	return identityCardType;
    }

    public void setIdentityCardType(final IdentityCardType identityCardType) {
	this.identityCardType = identityCardType;
    }

}
