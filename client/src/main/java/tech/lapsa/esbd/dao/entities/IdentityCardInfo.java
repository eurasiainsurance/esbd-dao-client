package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(59)
public class IdentityCardInfo extends Domain {

    private static final long serialVersionUID = 1L;

    // DOCUMENT_GIVED_DATE s:string Дата выдачи документа
    LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    // DOCUMENT_GIVED_BY s:string Документ выдан
    String issuingAuthority;

    public String getIssuingAuthority() {
	return issuingAuthority;
    }

    // DOCUMENT_NUMBER s:string Номер документа
    String number;

    public String getNumber() {
	return number;
    }

    // DOCUMENT_TYPE_ID s:int Тип документа (справочник DOCUMENTS_TYPES)
    int identityCardTypeId;
    IdentityCardType identityCardType;

    public IdentityCardType getIdentityCardType() {
	return identityCardType;
    }

    void setIdentityCardType(IdentityCardType identityCardType) {
	this.identityCardType = identityCardType;
    }
}
