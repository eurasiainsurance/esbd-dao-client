package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
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
	    this.name = MyStrings.requireNonEmpty(name, "name");
	    return this;
	}

	public PersonalInfoBuilder withSurename(final String surename) {
	    this.surename = MyStrings.requireNonEmpty(surename, "surename");
	    return this;
	}

	public PersonalInfoBuilder withPatronymic(final String patronymic) {
	    this.patronymic = MyStrings.requireNonEmpty(patronymic, "patronymic");
	    return this;
	}

	public PersonalInfoBuilder withDayOfBirth(final LocalDate dayOfBirth) {
	    this.dayOfBirth = MyObjects.requireNonNull(dayOfBirth, "dayOfBirth");
	    return this;
	}

	public PersonalInfoBuilder withGender(final Sex gender) {
	    this.gender = MyObjects.requireNonNull(gender, "gender");
	    return this;
	}

	public PersonalInfo build() {
	    return new PersonalInfo(name,
		    surename,
		    patronymic,
		    dayOfBirth,
		    gender);
	}

	public void buildTo(final Consumer<PersonalInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private PersonalInfo(final String name,
	    final String surename,
	    final String patronymic,
	    final LocalDate dayOfBirth,
	    final Sex gender) {
	this.name = name;
	this.surename = surename;
	this.patronymic = patronymic;
	this.dayOfBirth = dayOfBirth;
	this.gender = gender;
    }

    // name

    private final String name;

    public String getName() {
	return name;
    }

    // surename

    private final String surename;

    public String getSurename() {
	return surename;
    }

    // patronymic

    private final String patronymic;

    public String getPatronymic() {
	return patronymic;
    }

    // dayOfBirth

    private final LocalDate dayOfBirth;

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }

    // gender

    private final Sex gender;

    public Sex getGender() {
	return gender;
    }
}
