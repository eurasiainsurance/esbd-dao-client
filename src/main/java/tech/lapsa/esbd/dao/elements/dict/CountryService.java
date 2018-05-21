package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.international.country.Country;

import tech.lapsa.esbd.dao.elements.ElementsService;

public interface CountryService extends ElementsService<Country> {

    public static final String BEAN_NAME = "CountryServiceBean";

    @Local
    public interface CountryServiceLocal extends ElementsServiceLocal<Country>, CountryService {
    }

    @Remote
    public interface CountryServiceRemote extends ElementsServiceRemote<Country>, CountryService {
    }
}
