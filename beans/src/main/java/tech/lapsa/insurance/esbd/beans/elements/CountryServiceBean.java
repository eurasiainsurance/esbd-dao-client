package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.international.country.Country;

import tech.lapsa.insurance.esbd.beans.elements.mapping.CountryMapping;
import tech.lapsa.insurance.esbd.elements.CountryService.CountryServiceLocal;
import tech.lapsa.insurance.esbd.elements.CountryService.CountryServiceRemote;

@Singleton
public class CountryServiceBean extends AElementsService<Country, Integer>
	implements CountryServiceLocal, CountryServiceRemote {

    public CountryServiceBean() {
	super(CountryMapping.getInstance()::forId);
    }
}