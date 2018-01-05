package tech.lapsa.esbd.dao.entities;

import com.lapsa.international.country.Country;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.esbd.dao.Domain;
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
    boolean resident;

    public boolean isResident() {
	return resident;
    }

    // COUNTRY_ID s:int Страна (справочник COUNTRIES)
    int countryId;
    Country country;

    public Country getCountry() {
	return country;
    }

    void setCountry(Country country) {
	this.country = country;
    }

    // SETTLEMENT_ID s:int Населенный пункт (справочник SETTLEMENTS)
    int cityId;
    KZCity city;

    public KZCity getCity() {
	return city;
    }

    void setCity(KZCity city) {
	this.city = city;
    }

}
