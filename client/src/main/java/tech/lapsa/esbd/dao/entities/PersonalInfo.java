package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.esbd.dao.Domain;
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

    // First_Name s:string Имя (для физ. лица)
    String name;

    public String getName() {
	return name;
    }

    // Last_Name s:string Фамилия (для физ. лица)
    String surename;

    public String getSurename() {
	return surename;
    }

    // Middle_Name s:string Отчество (для физ. лица)
    String patronymic;

    public String getPatronymic() {
	return patronymic;
    }

    // Born s:string Дата рождения
    LocalDate dayOfBirth;

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }

    // Sex_ID s:int Пол (справочник SEX)
    int _gender;
    Sex gender;

    public Sex getGender() {
	return gender;
    }

    void setGender(final Sex gender) {
	this.gender = gender;
    }
}
