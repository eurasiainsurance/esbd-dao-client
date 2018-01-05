package tech.lapsa.esbd.dao.entities;

import com.lapsa.insurance.elements.SubjectType;
import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

/**
 * Абстрактный класс для представления лица
 *
 * @author vadim.isaev
 *
 */
public abstract class SubjectEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // ID s:int Идентификатор клиента (обязательно)
    Integer id;

    public Integer getId() {
	return id;
    }

    // Natural_Person_Bool s:int Признак принадлежности к физ. лицу (1 - физ.
    // лицо; 0 - юр. лицо)(обязательно)
    public abstract SubjectType getSubjectType();

    // RESIDENT_BOOL s:int Признак резидентства (обязательно)
    // COUNTRY_ID s:int Страна (справочник COUNTRIES)
    // SETTLEMENT_ID s:int Населенный пункт (справочник SETTLEMENTS)
    OriginInfo origin;

    public OriginInfo getOrigin() {
	return origin;
    }

    // PHONES s:string Номера телефонов
    // EMAIL s:string Адрес электронной почты
    // Address s:string Адрес
    // WWW s:string Сайт
    ContactInfo contact;

    public ContactInfo getContact() {
	return contact;
    }

    // TPRN s:string РНН
    String taxPayerNumber;

    public String getTaxPayerNumber() {
	return taxPayerNumber;
    }

    // DESCRIPTION s:string Примечание
    String comments;

    public String getComments() {
	return comments;
    }

    // RESIDENT_BOOL s:int Признак резидентства (обязательно)
    boolean resident;

    public boolean isResident() {
	return resident;
    }

    // IIN s:string ИИН/БИН
    String _idNumber;
    TaxpayerNumber idNumber;

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    // ECONOMICS_SECTOR_ID s:int Сектор экономики (справочник ECONOMICS_SECTORS)
    int _economicsSector;
    KZEconomicSector economicsSector;

    public KZEconomicSector getEconomicsSector() {
	return economicsSector;
    }

    void setEconomicsSector(final KZEconomicSector economicsSector) {
	this.economicsSector = economicsSector;
    }

    // BANKS tns:ArrayOfCLIENTBANK Содержит реквизиты банка клиента.
}
