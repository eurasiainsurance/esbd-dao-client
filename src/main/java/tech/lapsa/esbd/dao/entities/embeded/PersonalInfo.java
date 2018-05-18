package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для предсталвения основных персональных данных клиента - физического
 * лица
 *
 * @author vadim.isaev
 *
 */
@HashCodePrime(73)
public class PersonalInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final PersonalInfoBuilder builder() {
	return new PersonalInfoBuilder();
    }

    public static final class PersonalInfoBuilder {

	private String name;
	private String surename;
	private String patronymic;
	private LocalDate dayOfBirth;
	private Sex gender;

	private PersonalInfoBuilder() {
	}

	public PersonalInfoBuilder withName(final String name) {
	    this.name = name;
	    return this;
	}

	public PersonalInfoBuilder withSurename(final String surename) {
	    this.surename = surename;
	    return this;
	}

	public PersonalInfoBuilder withPatronymic(final String patronymic) {
	    this.patronymic = patronymic;
	    return this;
	}

	public PersonalInfoBuilder withDayOfBirth(final LocalDate dayOfBirth) {
	    this.dayOfBirth = dayOfBirth;
	    return this;
	}

	public PersonalInfoBuilder withGender(final Sex gender) {
	    this.gender = gender;
	    return this;
	}

	public PersonalInfoBuilder withGender(final Optional<Sex> optGender) {
	    return withGender(MyObjects.requireNonNull(optGender, "optGender").orElse(null));
	}

	public PersonalInfo build() {
	    final PersonalInfo res = new PersonalInfo();
	    res.name = name;
	    res.surename = surename;
	    res.patronymic = patronymic;
	    res.dayOfBirth = dayOfBirth;
	    res.gender = gender;
	    return res;
	}

	public void buildTo(final Consumer<PersonalInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private PersonalInfo() {
    }

    // name

    private String name;

    public String getName() {
	return name;
    }

    // surename

    private String surename;

    public String getSurename() {
	return surename;
    }

    // patronymic

    private String patronymic;

    public String getPatronymic() {
	return patronymic;
    }

    // dayOfBirth

    private LocalDate dayOfBirth;

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }

    // gender

    private Sex gender;

    public Sex getGender() {
	return gender;
    }
}
