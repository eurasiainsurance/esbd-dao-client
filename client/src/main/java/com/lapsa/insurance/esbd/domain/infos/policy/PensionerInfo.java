package com.lapsa.insurance.esbd.domain.infos.policy;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PensionerInfo {

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
    private LocalDate certiticateDateOfIssue;

    // GENERATED

    public String getCertificateNumber() {
	return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public LocalDate getCertiticateDateOfIssue() {
	return certiticateDateOfIssue;
    }

    public void setCertiticateDateOfIssue(LocalDate certiticateDateOfIssue) {
	this.certiticateDateOfIssue = certiticateDateOfIssue;
    }

}
