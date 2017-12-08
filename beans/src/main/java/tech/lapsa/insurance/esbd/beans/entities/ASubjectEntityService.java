package tech.lapsa.insurance.esbd.beans.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfClient;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.elements.CountryService.CountryServiceLocal;
import tech.lapsa.insurance.esbd.elements.KZCityService.KZCityServiceLocal;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService.KZEconomicSectorServiceLocal;
import tech.lapsa.insurance.esbd.entities.SubjectEntity;
import tech.lapsa.insurance.esbd.infos.ContactInfo;
import tech.lapsa.insurance.esbd.infos.OriginInfo;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public abstract class ASubjectEntityService {

    @EJB
    protected CountryServiceLocal countryService;

    @EJB
    protected KZEconomicSectorServiceLocal econimicsSectorService;

    @EJB
    protected KZCityServiceLocal cityService;

    @EJB
    protected ConnectionPool pool;

    protected Client fetchClientByIdNumber(TaxpayerNumber taxpayerNumber, boolean fetchNaturals,
	    boolean fetchCompanies) {
	int[] residentBools = new int[] { 1, 0 };
	List<Integer> naturalPersonBools = new ArrayList<>();
	if (fetchNaturals)
	    naturalPersonBools.add(1);
	if (fetchCompanies)
	    naturalPersonBools.add(0);

	try (Connection con = pool.getConnection()) {
	    for (int residentBool : residentBools) {
		for (int naturalPersonBool : naturalPersonBools) {
		    Client requestClient = new Client();
		    requestClient.setIIN(taxpayerNumber.getNumber());
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
	oi.setCountry(MyOptionals.of(source.getCOUNTRYID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> countryService.getById(id))) //
		.orElse(null));

	// non mandatory field
	oi.setCity(MyOptionals.of(source.getSETTLEMENTID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> cityService.getById(id))) //
		.orElse(null));

	// PHONES s:string Номера телефонов
	// EMAIL s:string Адрес электронной почты
	// Address s:string Адрес
	// WWW s:string Сайт
	ContactInfo cni = new ContactInfo();
	target.setContact(cni);
	if (source.getPHONES() != null)
	    cni.setPhone(PhoneNumber.assertValid(source.getPHONES()));
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
