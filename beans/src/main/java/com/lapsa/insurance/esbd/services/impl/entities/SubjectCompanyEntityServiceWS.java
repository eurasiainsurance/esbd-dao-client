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
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.general.CompanyActivityKindServiceDAO;
import com.lapsa.insurance.esbd.services.general.SubjectCompanyServiceDAO;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class SubjectCompanyEntityServiceWS extends SubjectEntityServiceWS implements SubjectCompanyServiceDAO {

    @EJB
    private CompanyActivityKindServiceDAO companyActivityKindService;

    @Inject
    private ESBDConnectionPool pool;

    @Override
    public SubjectCompanyEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (ESBDConnection con = pool.getConnection()) {
	    Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    boolean isNotPerson = source.getNaturalPersonBool() == 0;
	    if (!isNotPerson)
		throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with ID = '" + id
			+ "'. It was a " + SubjectPersonEntity.class.getName());
	    return convert(source);
	}
    }

    @Override
    public SubjectCompanyEntity getByBIN(String bin) throws NotFound {
	MyStrings.requireNonEmpty(bin, "bin");
	Client source = fetchClientByIdNumber(bin, false, true);
	if (source == null)
	    throw new NotFound(
		    SubjectCompanyEntity.class.getSimpleName() + " not found with 'bin' = '" + bin + "'");
	boolean isNotPerson = source.getNaturalPersonBool() == 0;
	if (!isNotPerson)
	    throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with 'bin' = '" + bin
		    + "'. It was a " + SubjectPersonEntity.class.getName());
	return convert(source);
    }

    SubjectCompanyEntity convert(Client source) {
	SubjectCompanyEntity target = new SubjectCompanyEntity();
	fillValues(source, target);
	return target;
    }

    void fillValues(Client source, SubjectCompanyEntity target) {
	fillValues(source, (SubjectEntity) target);

	// Juridical_Person_Name s:string Наименование (для юр. лица)
	target.setCompanyName(source.getJuridicalPersonName());

	// MAIN_CHIEF s:string Первый руководитель
	target.setHeadName(source.getMAINCHIEF());

	// MAIN_ACCOUNTANT s:string Главный бухгалтер
	target.setAccountantName(source.getMAINACCOUNTANT());

	// ACTIVITY_KIND_ID s:int Вид деятельности (справочник ACTIVITY_KINDS)
	target.setCompanyActivityKindId(new Long(source.getACTIVITYKINDID()));
	try {
	    if (MyNumbers.nonZero(source.getACTIVITYKINDID()))
		target.setCompanyActivityKind(companyActivityKindService.getById(new Long(source.getACTIVITYKINDID())));
	} catch (NotFound e) {
	    // non mandatory field
	}
    }
}
