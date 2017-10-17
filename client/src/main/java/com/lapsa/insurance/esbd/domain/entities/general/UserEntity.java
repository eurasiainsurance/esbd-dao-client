package com.lapsa.insurance.esbd.domain.entities.general;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserEntity {

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

    // ID s:int Идентификатор пользователя
    private long id;

    // Name s:string Имя пользователя
    private String login;

    // Branch_ID s:int Филиал пользователя (справочник BRANCHES)
    private BranchEntity branch;

    // CLIENT_ID s:int Клиент пользователя (справочник CLIENTS)
    private long subjectId;
    private SubjectEntity subject;

    // SYSTEM_DELIMITER_ID s:int Разделитель учета (справочник SYSTEM_DELIMITER)
    private InsuranceCompanyEntity organization;

    // IsAuthenticated s:int Пользователь аутентифицирован
    private boolean authentificated;

    // SessionID s:string Идентификатор текущей сессии пользователя
    private String lastSesionId;

    // ErrorMessage s:string Описание ошибки аутентификации
    // LastRequestTime s:string Время последнего действия пользователя

    // GENERATED

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public BranchEntity getBranch() {
	return branch;
    }

    public void setBranch(BranchEntity branch) {
	this.branch = branch;
    }

    public long getSubjectId() {
	return subjectId;
    }

    public void setSubjectId(long subjectId) {
	this.subjectId = subjectId;
    }

    public SubjectEntity getSubject() {
	return subject;
    }

    public void setSubject(SubjectEntity subject) {
	this.subject = subject;
    }

    public InsuranceCompanyEntity getOrganization() {
	return organization;
    }

    public void setOrganization(InsuranceCompanyEntity organization) {
	this.organization = organization;
    }

    public boolean isAuthentificated() {
	return authentificated;
    }

    public void setAuthentificated(boolean authentificated) {
	this.authentificated = authentificated;
    }

    public String getLastSesionId() {
	return lastSesionId;
    }

    public void setLastSesionId(String lastSesionId) {
	this.lastSesionId = lastSesionId;
    }
}
