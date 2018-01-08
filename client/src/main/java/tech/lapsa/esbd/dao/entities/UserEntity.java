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

    public Integer getId() {
	return id;
    }

    // Name s:string Имя пользователя
    String login;

    public String getLogin() {
	return login;
    }

    // Branch_ID s:int Филиал пользователя (справочник BRANCHES)
    int _branch;
    BranchEntity branch;

    public BranchEntity getBranch() {
	return branch;
    }

    void setBranch(final BranchEntity branch) {
	this.branch = branch;
    }

    // CLIENT_ID s:int Клиент пользователя (справочник CLIENTS)
    int _subject;
    SubjectEntity subject;

    public SubjectEntity getSubject() {
	return subject;
    }

    void setSubject(final SubjectEntity subject) {
	this.subject = subject;
    }

    // SYSTEM_DELIMITER_ID s:int Разделитель учета (справочник SYSTEM_DELIMITER)
    int _organization;
    InsuranceCompanyEntity organization;

    public InsuranceCompanyEntity getOrganization() {
	return organization;
    }

    void setOrganization(final InsuranceCompanyEntity organization) {
	this.organization = organization;
    }

    // IsAuthenticated s:int Пользователь аутентифицирован
    boolean authentificated;

    public boolean isAuthentificated() {
	return authentificated;
    }

    // SessionID s:string Идентификатор текущей сессии пользователя
    String lastSesionId;

    public String getLastSesionId() {
	return lastSesionId;
    }

    // ErrorMessage s:string Описание ошибки аутентификации
    // LastRequestTime s:string Время последнего действия пользователя
}
