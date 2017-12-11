package tech.lapsa.insurance.esbd.infos;

import java.time.LocalDate;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.insurance.esbd.Pojo;
import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для предсталвения основных персональных данных клиента - физического
 * лица
 * 
 * @author vadim.isaev
 *
 */
@HashCodePrime(73)
public class PersonalInfo extends Pojo {

    private static final long serialVersionUID = 1L;

    // First_Name s:string Имя (для физ. лица)
    private String name;

    // Last_Name s:string Фамилия (для физ. лица)
    private String surename;

    // Middle_Name s:string Отчество (для физ. лица)
    private String patronymic;

    // Born s:string Дата рождения
    private LocalDate dayOfBirth;

    // Sex_ID s:int Пол (справочник SEX)
    private Sex sex;

    // GENERATED

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSurename() {
	return surename;
    }

    public void setSurename(String surename) {
	this.surename = surename;
    }

    public String getPatronymic() {
	return patronymic;
    }

    public void setPatronymic(String patronymic) {
	this.patronymic = patronymic;
    }

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
	this.dayOfBirth = dayOfBirth;
    }

    public Sex getSex() {
	return sex;
    }

    public void setSex(Sex sex) {
	this.sex = sex;
    }

}
