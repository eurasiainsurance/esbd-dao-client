package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.esbd.dao.Domain;
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
public class PersonalInfo extends Domain {

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
	    return new PersonalInfo(name, surename, patronymic, dayOfBirth, gender);
	}

	public void buildTo(final Consumer<PersonalInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private PersonalInfo(final String name, final String surename, final String patronymic, final LocalDate dayOfBirth,
	    final Sex gender) {
	this.name = name;
	this.surename = surename;
	this.patronymic = patronymic;
	this.dayOfBirth = dayOfBirth;
	this.gender = gender;
    }

    // First_Name s:string Имя (для физ. лица)
    private final String name;

    public String getName() {
	return name;
    }

    // Last_Name s:string Фамилия (для физ. лица)
    private final String surename;

    public String getSurename() {
	return surename;
    }

    // Middle_Name s:string Отчество (для физ. лица)
    private final String patronymic;

    public String getPatronymic() {
	return patronymic;
    }

    // Born s:string Дата рождения
    private final LocalDate dayOfBirth;

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }

    // Sex_ID s:int Пол (справочник SEX)
    private final Sex gender;

    public Sex getGender() {
	return gender;
    }
}
