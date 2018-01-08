package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.international.country.Country;

public interface CountryService extends ElementsService<Country, Integer> {

    public static final String BEAN_NAME = "CountryServiceBean";

    @Local
    public interface CountryServiceLocal extends ElementsServiceLocal<Country, Integer>, CountryService {
    }

    @Remote
    public interface CountryServiceRemote extends ElementsServiceRemote<Country, Integer>, CountryService {
    }
}
