package tech.lapsa.insurance.esbd.infos;

import com.lapsa.international.country.Country;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.esbd.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для представления данных о происхождении клиента
 * 
 * @author vadim.isaev
 *
 */
@HashCodePrime(67)
public class OriginInfo extends Domain {

    private static final long serialVersionUID = 1L;

    // RESIDENT_BOOL s:int Признак резидентства (обязательно)
    private boolean resident;

    // COUNTRY_ID s:int Страна (справочник COUNTRIES)
    private Country country;

    // SETTLEMENT_ID s:int Населенный пункт (справочник SETTLEMENTS)
    private KZCity city;

    // GENERATED

    public boolean isResident() {
	return resident;
    }

    public void setResident(boolean resident) {
	this.resident = resident;
    }

    public Country getCountry() {
	return country;
    }

    public void setCountry(Country country) {
	this.country = country;
    }

    public KZCity getCity() {
	return city;
    }

    public void setCity(KZCity city) {
	this.city = city;
    }
}
