package tech.lapsa.esbd.dao.entities;

import java.time.Instant;
import java.util.Optional;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.dict.BranchEntity;
import tech.lapsa.esbd.dao.dict.InsuranceCompanyEntity;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(29)
public class UserEntity extends Domain {

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

	public UserEntityBuilder withBranch(final Optional<BranchEntity> optBranch) throws IllegalArgumentException {
	    if (MyObjects.requireNonNull(optBranch, "optBranch").isPresent())
		return withBranch(optBranch.get());
	    branch = null;
	    return this;
	}

	public UserEntityBuilder withSubject(final SubjectEntity subject) throws IllegalArgumentException {
	    this.subject = MyObjects.requireNonNull(subject, "subject");
	    return this;
	}

	public UserEntityBuilder withSubject(final Optional<SubjectEntity> optSubject) throws IllegalArgumentException {
	    if (MyObjects.requireNonNull(optSubject, "optSubject").isPresent())
		return withSubject(optSubject.get());
	    subject = null;
	    return this;
	}

	public UserEntityBuilder withOrganization(final InsuranceCompanyEntity organization)
		throws IllegalArgumentException {
	    this.organization = organization;
	    return this;
	}

	public UserEntityBuilder withOrganization(final Optional<InsuranceCompanyEntity> optOrganization)
		throws IllegalArgumentException {
	    if (MyObjects.requireNonNull(optOrganization, "optOrganization").isPresent())
		return withOrganization(optOrganization.get());
	    organization = null;
	    return this;
	}

	public UserEntityBuilder withAuthentificated(final boolean authentificated) {
	    this.authentificated = authentificated;
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

	public UserEntityBuilder withLastActivity(final Optional<Instant> optLastActivity) {
	    if (MyObjects.requireNonNull(optLastActivity, "optLastActivity").isPresent())
		return withLastActivity(optLastActivity.get());
	    lastActivity = null;
	    return this;
	}

	public UserEntity build() throws IllegalArgumentException {
	    final UserEntity res = new UserEntity();
	    res.id = MyNumbers.requirePositive(id, "id");
	    res.login = MyStrings.requireNonEmpty(login, "login");
	    res.branch = branch;
	    res.subject = subject;
	    res.organization = organization;
	    res.authentificated = authentificated;
	    res.lastSesionId = lastSesionId;
	    res.lastActivity = lastActivity;
	    return res;
	}

	public void buildTo(final Consumer<UserEntity> consumer) throws IllegalArgumentException {
	    consumer.accept(build());
	}
    }

    private UserEntity() {
    }

    // id

    private Integer id;

    public Integer getId() {
	return id;
    }

    // login

    private String login;

    public String getLogin() {
	return login;
    }

    // branch

    private BranchEntity branch;

    public BranchEntity getBranch() {
	return branch;
    }

    // subject

    private SubjectEntity subject;

    public SubjectEntity getSubject() {
	return subject;
    }

    // organization

    private InsuranceCompanyEntity organization;

    public InsuranceCompanyEntity getOrganization() {
	return organization;
    }

    // authentificated

    private boolean authentificated;

    public boolean isAuthentificated() {
	return authentificated;
    }

    // lastSesionId

    private String lastSesionId;

    public String getLastSesionId() {
	return lastSesionId;
    }

    // lastActivity
    private Instant lastActivity;

    public Instant getLastActivity() {
	return lastActivity;
    }
}
