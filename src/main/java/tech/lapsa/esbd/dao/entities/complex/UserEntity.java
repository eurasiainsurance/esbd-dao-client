package tech.lapsa.esbd.dao.entities.complex;

import java.time.Instant;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.esbd.dao.entities.dict.BranchEntity;
import tech.lapsa.esbd.dao.entities.dict.InsuranceCompanyEntity;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(29)
public class UserEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final UserEntityBuilder builder() {
	return new UserEntityBuilder();
    }

    public static final class UserEntityBuilder {

	private Integer id;
	private String login;
	private BranchEntity branch;
	private SubjectEntity subject;
	private InsuranceCompanyEntity organization;
	private boolean authentificated;
	private String lastSesionId;
	private Instant lastActivity;

	private UserEntityBuilder() {
	}

	public UserEntityBuilder withId(final Integer id) throws IllegalArgumentException {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public UserEntityBuilder withLogin(final String login) throws IllegalArgumentException {
	    this.login = MyStrings.requireNonEmpty(login, "login");
	    return this;
	}

	public UserEntityBuilder withBranch(final BranchEntity branch) throws IllegalArgumentException {
	    this.branch = MyObjects.requireNonNull(branch, "branch");
	    return this;
	}

	public UserEntityBuilder withSubject(final SubjectEntity subject) throws IllegalArgumentException {
	    this.subject = MyObjects.requireNonNull(subject, "subject");
	    return this;
	}

	public UserEntityBuilder withOrganization(final InsuranceCompanyEntity organization)
		throws IllegalArgumentException {
	    this.organization = MyObjects.requireNonNull(organization, "organization");
	    return this;
	}

	public UserEntityBuilder withAuthentificated(final Boolean authentificated) {
	    this.authentificated = MyObjects.requireNonNull(authentificated, "authentificated");
	    return this;
	}

	public UserEntityBuilder withLastSesionId(final String lastSesionId) {
	    this.lastSesionId = lastSesionId;
	    return this;
	}

	public UserEntityBuilder withLastActivity(final Instant lastActivity) {
	    this.lastActivity = MyObjects.requireNonNull(lastActivity, "lastActivity");
	    return this;
	}

	public UserEntity build() throws IllegalArgumentException {
	    return new UserEntity(id,
		    login,
		    branch,
		    subject,
		    organization,
		    authentificated,
		    lastSesionId,
		    lastActivity);
	}

	public void buildTo(final Consumer<UserEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    // constructor

    private UserEntity(final Integer id,
	    final String login,
	    final BranchEntity branch,
	    final SubjectEntity subject,
	    final InsuranceCompanyEntity organization,
	    final Boolean authentificated,
	    final String lastSesionId,
	    final Instant lastActivity) {
	this.id = id;
	this.login = login;
	this.branch = branch;
	this.subject = subject;
	this.organization = organization;
	this.authentificated = authentificated;
	this.lastSesionId = lastSesionId;
	this.lastActivity = lastActivity;
    }

    // id

    private final Integer id;

    public Integer getId() {
	return id;
    }

    // login

    private final String login;

    public String getLogin() {
	return login;
    }

    // branch

    private final BranchEntity branch;

    public BranchEntity getBranch() {
	return branch;
    }

    // subject

    private final SubjectEntity subject;

    public SubjectEntity getSubject() {
	return subject;
    }

    // organization

    private final InsuranceCompanyEntity organization;

    public InsuranceCompanyEntity getOrganization() {
	return organization;
    }

    // authentificated

    private final Boolean authentificated;

    public Boolean isAuthentificated() {
	return authentificated;
    }

    // lastSesionId

    private final String lastSesionId;

    public String getLastSesionId() {
	return lastSesionId;
    }

    // lastActivity

    private final Instant lastActivity;

    public Instant getLastActivity() {
	return lastActivity;
    }
}
