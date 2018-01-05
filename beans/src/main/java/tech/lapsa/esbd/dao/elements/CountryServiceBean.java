package tech.lapsa.esbd.dao.elements;

import javax.ejb.Singleton;

import com.lapsa.international.country.Country;

import tech.lapsa.esbd.dao.elements.CountryService;
import tech.lapsa.esbd.dao.elements.CountryService.CountryServiceLocal;
import tech.lapsa.esbd.dao.elements.CountryService.CountryServiceRemote;
import tech.lapsa.esbd.dao.elements.mapping.CountryMapping;

@Singleton(name = CountryService.BEAN_NAME)
public class CountryServiceBean extends AElementsService<Country, Integer>
	implements CountryServiceLocal, CountryServiceRemote {

    public CountryServiceBean() {
	super(CountryMapping.getInstance()::forId, CountryService.class);
    }
}