package tech.lapsa.insurance.esbd.entities;

import tech.lapsa.insurance.esbd.Pojo;
import tech.lapsa.insurance.esbd.dict.BranchEntity;
import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntity;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(29)
public class UserEntity extends Pojo {

    private static final long serialVersionUID = 1L;

    // ID s:int Идентификатор пользователя
    private Integer id;

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

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
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
