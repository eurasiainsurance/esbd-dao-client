package tech.lapsa.esbd.dao.entities;

import com.lapsa.insurance.elements.SubjectType;
import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.infos.ContactInfo;
import tech.lapsa.esbd.dao.infos.OriginInfo;

/**
 * Абстрактный класс для представления лица
 *
 * @author vadim.isaev
 *
 */
public abstract class SubjectEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // ID s:int Идентификатор клиента (обязательно)
    private Integer id;

    // Natural_Person_Bool s:int Признак принадлежности к физ. лицу (1 - физ.
    // лицо; 0 - юр. лицо)(обязательно)
    public abstract SubjectType getSubjectType();

    // RESIDENT_BOOL s:int Признак резидентства (обязательно)
    // COUNTRY_ID s:int Страна (справочник COUNTRIES)
    // SETTLEMENT_ID s:int Населенный пункт (справочник SETTLEMENTS)
    private OriginInfo origin = new OriginInfo();

    // PHONES s:string Номера телефонов
    // EMAIL s:string Адрес электронной почты
    // Address s:string Адрес
    // WWW s:string Сайт
    private ContactInfo contact = new ContactInfo();

    // TPRN s:string РНН
    private String taxPayerNumber;

    // DESCRIPTION s:string Примечание
    private String comments;

    // RESIDENT_BOOL s:int Признак резидентства (обязательно)
    private boolean resident;

    // IIN s:string ИИН/БИН
    private String idNumber;

    // BANKS tns:ArrayOfCLIENTBANK Содержит реквизиты банка клиента.

    // ECONOMICS_SECTOR_ID s:int Сектор экономики (справочник ECONOMICS_SECTORS)
    private KZEconomicSector economicsSector;

    // GENERATED

    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    public OriginInfo getOrigin() {
	return origin;
    }

    public void setOrigin(final OriginInfo origin) {
	this.origin = origin;
    }

    public ContactInfo getContact() {
	return contact;
    }

    public void setContact(final ContactInfo contact) {
	this.contact = contact;
    }

    public String getTaxPayerNumber() {
	return taxPayerNumber;
    }

    public void setTaxPayerNumber(final String taxPayerNumber) {
	this.taxPayerNumber = taxPayerNumber;
    }

    public String getComments() {
	return comments;
    }

    public void setComments(final String comments) {
	this.comments = comments;
    }

    public boolean isResident() {
	return resident;
    }

    public void setResident(final boolean resident) {
	this.resident = resident;
    }

    public String getIdNumber() {
	return idNumber;
    }

    public void setIdNumber(final String idNumber) {
	this.idNumber = idNumber;
    }

    public KZEconomicSector getEconomicsSector() {
	return economicsSector;
    }

    public void setEconomicsSector(final KZEconomicSector economicsSector) {
	this.economicsSector = economicsSector;
    }

}
