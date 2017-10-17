package com.lapsa.insurance.esbd.services.impl.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfClient;
import com.lapsa.esbd.jaxws.client.Client;
import com.lapsa.insurance.esbd.domain.entities.general.SubjectEntity;
import com.lapsa.insurance.esbd.domain.infos.general.ContactInfo;
import com.lapsa.insurance.esbd.domain.infos.general.OriginInfo;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.CountryServiceDAO;
import com.lapsa.insurance.esbd.services.elements.KZCityServiceDAO;
import com.lapsa.insurance.esbd.services.elements.KZEconomicSectorServiceDAO;
import com.lapsa.insurance.esbd.services.general.SubjectCompanyServiceDAO;
import com.lapsa.insurance.esbd.services.general.SubjectPersonServiceDAO;
import com.lapsa.insurance.esbd.services.general.SubjectServiceDAO;
import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class SubjectEntityServiceWS extends AbstractESBDEntityServiceWS implements SubjectServiceDAO {

    @EJB
    private SubjectPersonServiceDAO subjectPersonService;

    @EJB
    private SubjectCompanyServiceDAO subjectCompanyService;

    @EJB
    private CountryServiceDAO countryService;

    @EJB
    private KZEconomicSectorServiceDAO econimicsSectorService;

    @EJB
    private KZCityServiceDAO cityService;

    @Inject
    private ESBDConnectionPool pool;

    public SubjectEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");

	try (ESBDConnection con = pool.getConnection()) {
	    Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with ID = '" + id + "'");

	    if (source.getNaturalPersonBool() == 1) {
		// частник SubjectPerson
		return subjectPersonService.getById(id);
	    } else {
		// юрлицо SubjectCompany
		return subjectCompanyService.getById(id);
	    }
	}
    }

    @Override
    public SubjectEntity getByIdNumber(String idNumber) throws NotFound {
	MyStrings.requireNonEmpty(idNumber, "idNumber");
	Client source = fetchClientByIdNumber(idNumber, true, true);
	if (source == null)
	    throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with IDNumber = '" + idNumber + "'");
	if (source.getNaturalPersonBool() == 1) {
	    // частник SubjectPerson
	    return subjectPersonService.getByIIN(idNumber);
	} else {
	    // юрлицо SubjectCompany
	    return subjectCompanyService.getByBIN(idNumber);
	}
    }

    protected Client fetchClientByIdNumber(String idNumber, boolean fetchNaturals, boolean fetchCompanies) {
	int[] residentBools = new int[] { 1, 0 };
	List<Integer> naturalPersonBools = new ArrayList<>();
	if (fetchNaturals)
	    naturalPersonBools.add(1);
	if (fetchCompanies)
	    naturalPersonBools.add(0);

	try (ESBDConnection con = pool.getConnection()) {
	    for (int residentBool : residentBools) {
		for (int naturalPersonBool : naturalPersonBools) {
		    Client requestClient = new Client();
		    requestClient.setIIN(idNumber);
		    requestClient.setNaturalPersonBool(naturalPersonBool);
		    requestClient.setRESIDENTBOOL(residentBool);
		    ArrayOfClient clients = con.getClientsByKeyFields(requestClient);
		    if (clients != null && clients.getClient() != null && clients.getClient().size() > 0)
			return clients.getClient().iterator().next();
		}
	    }
	}
	return null;
    }

    void fillValues(Client source, SubjectEntity target) {
	// ID s:int Идентификатор клиента (обязательно)
	target.setId(source.getID());

	// RESIDENT_BOOL s:int Признак резидентства (обязательно)
	// COUNTRY_ID s:int Страна (справочник COUNTRIES)
	// SETTLEMENT_ID s:int Населенный пункт (справочник SETTLEMENTS)
	OriginInfo oi = new OriginInfo();
	target.setOrigin(oi);
	oi.setResident(source.getRESIDENTBOOL() == 1);
	try {
	    if (MyNumbers.nonZero(source.getCOUNTRYID()))
		oi.setCountry(countryService.getById(source.getCOUNTRYID()));
	} catch (NotFound e) {
	    // non mandatory field
	}
	try {
	    if (MyNumbers.nonZero(source.getSETTLEMENTID()))
		oi.setCity(cityService.getById(source.getSETTLEMENTID()));
	} catch (NotFound e) {
	    // non mandatory field
	}

	// PHONES s:string Номера телефонов
	// EMAIL s:string Адрес электронной почты
	// Address s:string Адрес
	// WWW s:string Сайт
	ContactInfo cni = new ContactInfo();
	target.setContact(cni);
	if (source.getPHONES() != null)
	    cni.setPhone(PhoneNumber.parse(source.getPHONES()));
	cni.setEmail(source.getEMAIL());
	cni.setHomeAdress(source.getAddress());
	cni.setSiteUrl(source.getWWW());

	// TPRN s:string РНН
	target.setTaxPayerNumber(source.getTPRN());

	// DESCRIPTION s:string Примечание
	target.setComments(source.getDESCRIPTION());

	// RESIDENT_BOOL s:int Признак резидентства (обязательно)
	target.setResident(source.getRESIDENTBOOL() == 1);

	// IIN s:string ИИН/БИН
	target.setIdNumber(source.getIIN());

	// ECONOMICS_SECTOR_ID s:int Сектор экономики (справочник
	// ECONOMICS_SECTORS)
	try {
	    target.setEconomicsSector(econimicsSectorService.getById(source.getECONOMICSSECTORID()));
	} catch (NotFound e) {
	    // mandatory field
	    throw new DataCoruptionException(
		    "Error while fetching Company Client ID = '" + source.getID()
			    + "' from ESBD. Economics Sector ID = '" + source.getECONOMICSSECTORID() + "' not found",
		    e);
	}
    }

}
