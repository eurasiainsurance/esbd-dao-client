package com.lapsa.insurance.esbd.services.impl.entities;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.Client;
import com.lapsa.insurance.esbd.domain.entities.general.SubjectCompanyEntity;
import com.lapsa.insurance.esbd.domain.entities.general.SubjectEntity;
import com.lapsa.insurance.esbd.domain.entities.general.SubjectPersonEntity;
import com.lapsa.insurance.esbd.domain.infos.general.IdentityCardInfo;
import com.lapsa.insurance.esbd.domain.infos.general.PersonalInfo;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.IdentityCardTypeServiceDAO;
import com.lapsa.insurance.esbd.services.elements.SexServiceDAO;
import com.lapsa.insurance.esbd.services.general.SubjectPersonServiceDAO;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class SubjectPersonEntityServiceWS extends SubjectEntityServiceWS implements SubjectPersonServiceDAO {

    @EJB
    private IdentityCardTypeServiceDAO identityCardTypeService;

    @EJB
    private SexServiceDAO sexService;

    @Inject
    private ESBDConnectionPool pool;

    @Override
    public SubjectPersonEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");

	try (ESBDConnection con = pool.getConnection()) {
	    Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectPersonEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    boolean isNotPerson = source.getNaturalPersonBool() == 0;
	    if (isNotPerson)
		throw new NotFound(SubjectPersonEntity.class.getSimpleName() + " not found with ID = '" + id
			+ "'. It was a " + SubjectCompanyEntity.class.getName());
	    return convert(source);
	}
    }

    @Override
    public SubjectPersonEntity getByIIN(String iin) throws NotFound {
	MyStrings.requireNonEmpty(iin, "iin");
	Client source = fetchClientByIdNumber(iin, true, false);
	if (source == null)
	    throw new NotFound(
		    SubjectPersonEntity.class.getSimpleName() + " not found with 'iin' = '" + iin + "'");
	boolean isNotPerson = source.getNaturalPersonBool() == 0;
	if (isNotPerson)
	    throw new NotFound(SubjectPersonEntity.class.getSimpleName() + " not found with 'iin' = '" + iin
		    + "'. It was a " + SubjectCompanyEntity.class.getName());
	return convert(source);
    }

    SubjectPersonEntity convert(Client source) {
	SubjectPersonEntity target = new SubjectPersonEntity();
	fillValues(source, target);
	return target;
    }

    void fillValues(Client source, SubjectPersonEntity target) {
	fillValues(source, (SubjectEntity) target);

	// First_Name s:string Имя (для физ. лица)
	// Last_Name s:string Фамилия (для физ. лица)
	// Middle_Name s:string Отчество (для физ. лица)
	// Born s:string Дата рождения
	// Sex_ID s:int Пол (справочник SEX)
	PersonalInfo pi = new PersonalInfo();
	target.setPersonal(pi);
	pi.setName(source.getFirstName());
	pi.setSurename(source.getLastName());
	pi.setPatronymic(source.getMiddleName());
	pi.setDayOfBirth(convertESBDDateToLocalDate(source.getBorn()));
	try {
	    pi.setSex(sexService.getById(source.getSexID()));
	} catch (NotFound e) {
	    // non mandatory
	    pi.setSex(null);
	}

	// DOCUMENT_TYPE_ID s:int Тип документа (справочник DOCUMENTS_TYPES)
	// DOCUMENT_NUMBER s:string Номер документа
	// DOCUMENT_GIVED_BY s:string Документ выдан
	// DOCUMENT_GIVED_DATE s:string Дата выдачи документа
	IdentityCardInfo di = new IdentityCardInfo();
	target.setIdentityCardInfo(di);
	di.setDateOfIssue(convertESBDDateToCalendar(source.getDOCUMENTGIVEDDATE()));
	di.setIssuingAuthority(source.getDOCUMENTGIVEDBY());
	di.setNumber(source.getDOCUMENTNUMBER());
	try {
	    di.setIdentityCardType(identityCardTypeService
		    .getById(source.getDOCUMENTTYPEID()));
	} catch (NotFound e) {
	    // non mandatory
	    di.setIdentityCardType(null);
	}
    }
}
