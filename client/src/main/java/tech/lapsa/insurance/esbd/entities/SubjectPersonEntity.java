package tech.lapsa.insurance.esbd.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.lapsa.insurance.elements.SubjectType;

import tech.lapsa.insurance.esbd.infos.IdentityCardInfo;
import tech.lapsa.insurance.esbd.infos.PersonalInfo;

/**
 * Класс для представления данных о субъекте - физ.лице
 * 
 * @author vadim.isaev
 *
 */
public class SubjectPersonEntity extends SubjectEntity {

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    // First_Name s:string Имя (для физ. лица)
    // Last_Name s:string Фамилия (для физ. лица)
    // Middle_Name s:string Отчество (для физ. лица)
    // Born s:string Дата рождения
    // Sex_ID s:int Пол (справочник SEX)
    private PersonalInfo personal = new PersonalInfo();

    // DOCUMENT_TYPE_ID s:int Тип документа (справочник DOCUMENTS_TYPES)
    // DOCUMENT_NUMBER s:string Номер документа
    // DOCUMENT_GIVED_BY s:string Документ выдан
    // DOCUMENT_GIVED_DATE s:string Дата выдачи документа
    private IdentityCardInfo identityCardInfo = new IdentityCardInfo();

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.PERSON;
    }

    // GENERATED

    public PersonalInfo getPersonal() {
	return personal;
    }

    public void setPersonal(PersonalInfo personal) {
	this.personal = personal;
    }

    public IdentityCardInfo getIdentityCard() {
	return identityCardInfo;
    }

    public void setIdentityCardInfo(IdentityCardInfo identityCardInfo) {
	this.identityCardInfo = identityCardInfo;
    }

}
