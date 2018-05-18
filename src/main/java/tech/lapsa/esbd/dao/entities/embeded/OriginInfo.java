package tech.lapsa.esbd.dao.entities.embeded;

import java.util.Optional;
import java.util.function.Consumer;

import com.lapsa.international.country.Country;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для представления данных о происхождении клиента
 *
 * @author vadim.isaev
 *
 */
@HashCodePrime(67)
public class OriginInfo extends AEntity {

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
	    if (MyObjects.requireNonNull(optCountry, "optCountry").isPresent())
		return withCountry(optCountry.get());
	    country = null;
	    return this;
	}

	public OriginInfoBuilder withCity(final KZCity city) {
	    this.city = city;
	    return this;
	}

	public OriginInfoBuilder withCity(final Optional<KZCity> optCity) {
	    if (MyObjects.requireNonNull(optCity, "optCity").isPresent())
		return withCity(optCity.get());
	    city = null;
	    return this;
	}

	public OriginInfo build() {
	    final OriginInfo res = new OriginInfo();
	    res.resident = resident;
	    res.country = country;
	    res.city = city;
	    return res;
	}

	public void buildTo(final Consumer<OriginInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private OriginInfo() {
    }

    // resident

    private boolean resident;

    public boolean isResident() {
	return resident;
    }

    // country

    private Country country;

    public Country getCountry() {
	return country;
    }

    // city

    private KZCity city;

    public KZCity getCity() {
	return city;
    }
}
