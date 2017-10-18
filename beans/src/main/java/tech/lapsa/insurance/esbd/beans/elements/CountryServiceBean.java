package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.international.country.Country;

import tech.lapsa.insurance.esbd.beans.elements.mapping.CountryMapping;
import tech.lapsa.insurance.esbd.elements.CountryService;

@Singleton
public class CountryServiceBean extends AElementsService<Country, Integer> implements CountryService {

    public CountryServiceBean() {
	super(CountryMapping.getInstance()::forId);
    }
}