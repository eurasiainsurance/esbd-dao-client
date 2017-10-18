package tech.lapsa.insurance.esbd.beans.entities;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfClient;
import com.lapsa.esbd.jaxws.client.Client;
import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.elements.CountryService;
import tech.lapsa.insurance.esbd.elements.KZCityService;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService;
import tech.lapsa.insurance.esbd.entities.SubjectEntity;
import tech.lapsa.insurance.esbd.infos.ContactInfo;
import tech.lapsa.insurance.esbd.infos.OriginInfo;
import tech.lapsa.java.commons.function.MyNumbers;

public abstract class ASubjectEntityService {

    @Inject
    protected CountryService countryService;

    @Inject
    protected KZEconomicSectorService econimicsSectorService;

    @Inject
    protected KZCityService cityService;

    @Inject
    protected ESBDConnectionPool pool;

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

    protected void fillValues(Client source, SubjectEntity target) {
	// ID s:int Идентификатор клиента (обязательно)
	target.setId(source.getID());

	// RESIDENT_BOOL s:int Признак резидентства (обязательно)
	// COUNTRY_ID s:int Страна (справочник COUNTRIES)
	// SETTLEMENT_ID s:int Населенный пункт (справочник SETTLEMENTS)
	OriginInfo oi = new OriginInfo();
	target.setOrigin(oi);
	oi.setResident(source.getRESIDENTBOOL() == 1);

	// non mandatory field
	if (MyNumbers.nonZero(source.getCOUNTRYID()))
	    oi.setCountry(countryService.optionalById(source.getCOUNTRYID()).orElse(null));
	// non mandatory field
	if (MyNumbers.nonZero(source.getSETTLEMENTID()))
	    oi.setCity(cityService.optionalById(source.getSETTLEMENTID()).orElse(null));

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