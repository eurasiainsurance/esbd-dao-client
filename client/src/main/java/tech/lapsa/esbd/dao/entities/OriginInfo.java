package tech.lapsa.esbd.dao.entities;

import java.util.Optional;
import java.util.function.Consumer;

import com.lapsa.international.country.Country;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.java.commons.function.MyObjects;
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

    public static final OriginInfoBuilder builder() {
	return new OriginInfoBuilder();
    }

    public static final class OriginInfoBuilder {

	private boolean resident;
	private Country country;
	private KZCity city;

	private OriginInfoBuilder() {
	}

	public OriginInfoBuilder withResident(final boolean resident) {
	    this.resident = resident;
	    return this;
	}

	public OriginInfoBuilder withCountry(final Country country) {
	    this.country = country;
	    return this;
	}

	public OriginInfoBuilder withCountry(final Optional<Country> optCountry) {
	    return withCountry(MyObjects.requireNonNull(optCountry, "optCountry").orElse(null));
	}

	public OriginInfoBuilder withCity(final KZCity city) {
	    this.city = city;
	    return this;
	}

	public OriginInfoBuilder withCity(final Optional<KZCity> optCity) {
	    return withCity(MyObjects.requireNonNull(optCity, "optCity").orElse(null));
	}

	public OriginInfo build() {
	    return new OriginInfo(resident, country, city);
	}

	public void buildTo(final Consumer<OriginInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private OriginInfo(final boolean resident, final Country country, final KZCity city) {
	this.resident = resident;
	this.country = country;
	this.city = city;
    }

    // RESIDENT_BOOL s:int Признак резидентства (обязательно)
    private final boolean resident;

    public boolean isResident() {
	return resident;
    }

    // COUNTRY_ID s:int Страна (справочник COUNTRIES)
    private final Country country;

    public Country getCountry() {
	return country;
    }

    // SETTLEMENT_ID s:int Населенный пункт (справочник SETTLEMENTS)
    private final KZCity city;

    public KZCity getCity() {
	return city;
    }
}
