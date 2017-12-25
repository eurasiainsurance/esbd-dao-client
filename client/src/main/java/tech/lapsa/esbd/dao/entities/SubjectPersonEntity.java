package tech.lapsa.esbd.dao.entities;

import com.lapsa.insurance.elements.SubjectType;

import tech.lapsa.esbd.dao.infos.IdentityCardInfo;
import tech.lapsa.esbd.dao.infos.PersonalInfo;
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

    public void setPersonal(final PersonalInfo personal) {
	this.personal = personal;
    }

    public IdentityCardInfo getIdentityCard() {
	return identityCardInfo;
    }

    public void setIdentityCardInfo(final IdentityCardInfo identityCardInfo) {
	this.identityCardInfo = identityCardInfo;
    }

}
