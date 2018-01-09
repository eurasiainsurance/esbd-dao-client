package tech.lapsa.esbd.dao.entities;

import com.lapsa.insurance.elements.SubjectType;

import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для представления данных о субъекте - физ.лице
 *
 * @author vadim.isaev
 *
 */
@HashCodePrime(23)
public class SubjectPersonEntity extends SubjectEntity {

    private static final long serialVersionUID = 1L;

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.PERSON;
    }

    // First_Name s:string Имя (для физ. лица)
    // Last_Name s:string Фамилия (для физ. лица)
    // Middle_Name s:string Отчество (для физ. лица)
    // Born s:string Дата рождения
    // Sex_ID s:int Пол (справочник SEX)
    PersonalInfo personal;

    public PersonalInfo getPersonal() {
	return personal;
    }

    // DOCUMENT_TYPE_ID s:int Тип документа (справочник DOCUMENTS_TYPES)
    // DOCUMENT_NUMBER s:string Номер документа
    // DOCUMENT_GIVED_BY s:string Документ выдан
    // DOCUMENT_GIVED_DATE s:string Дата выдачи документа
    IdentityCardInfo identityCard;

    public IdentityCardInfo getIdentityCard() {
	return identityCard;
    }
}
