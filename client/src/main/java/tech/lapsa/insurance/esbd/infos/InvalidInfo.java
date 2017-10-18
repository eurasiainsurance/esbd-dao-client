package tech.lapsa.insurance.esbd.infos;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class InvalidInfo {

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

    private String certificateNumber;
    private LocalDate certificateValidFrom;
    private LocalDate certificateValidTill;

    // GENERATED

    public String getCertificateNumber() {
	return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public LocalDate getCertificateValidFrom() {
	return certificateValidFrom;
    }

    public void setCertificateValidFrom(LocalDate certificateValidFrom) {
	this.certificateValidFrom = certificateValidFrom;
    }

    public LocalDate getCertificateValidTill() {
	return certificateValidTill;
    }

    public void setCertificateValidTill(LocalDate certificateValidTill) {
	this.certificateValidTill = certificateValidTill;
    }

}
