package tech.lapsa.esbd.dao.entities.embeded;

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

	private Country country;
	private KZCity city;

	private OriginInfoBuilder() {
	}

	public OriginInfoBuilder withCountry(final Country country) {
	    this.country = MyObjects.requireNonNull(country, "country");
	    return this;
	}

	public OriginInfoBuilder withCity(final KZCity city) {
	    this.city = MyObjects.requireNonNull(city, "city");
	    return this;
	}

	public OriginInfo build() {
	    return new OriginInfo(country, city);
	}

	public void buildTo(final Consumer<OriginInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private OriginInfo(final Country country,
	    final KZCity city) {
	this.country = country;
	this.city = city;
    }

    // country

    private final Country country;

    public Country getCountry() {
	return country;
    }

    // city

    private final KZCity city;

    public KZCity getCity() {
	return city;
    }
}
