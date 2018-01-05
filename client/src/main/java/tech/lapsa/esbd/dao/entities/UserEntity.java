package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(29)
public class UserEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // ID s:int Идентификатор пользователя
    Integer id;

    // Name s:string Имя пользователя
    String login;

    // Branch_ID s:int Филиал пользователя (справочник BRANCHES)
    int branchId;
    BranchEntity branch;

    // CLIENT_ID s:int Клиент пользователя (справочник CLIENTS)
    int subjectId;
    SubjectEntity subject;

    // SYSTEM_DELIMITER_ID s:int Разделитель учета (справочник SYSTEM_DELIMITER)
    int organizationId;
    InsuranceCompanyEntity organization;

    // IsAuthenticated s:int Пользователь аутентифицирован
    boolean authentificated;

    // SessionID s:string Идентификатор текущей сессии пользователя
    String lastSesionId;

    // ErrorMessage s:string Описание ошибки аутентификации
    // LastRequestTime s:string Время последнего действия пользователя

    // GENERATED

    public Integer getId() {
	return id;
    }

    public String getLogin() {
	return login;
    }

    public BranchEntity getBranch() {
	return branch;
    }

    public SubjectEntity getSubject() {
	return subject;
    }

    public InsuranceCompanyEntity getOrganization() {
	return organization;
    }

    public boolean isAuthentificated() {
	return authentificated;
    }

    public String getLastSesionId() {
	return lastSesionId;
    }

    // just for lambda method references

    void setSubject(final SubjectEntity subject) {
	this.subject = subject;
    }

    void setBranch(final BranchEntity branch) {
	this.branch = branch;
    }

    void setOrganization(final InsuranceCompanyEntity organization) {
	this.organization = organization;
    }
}
