package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.international.country.Country;

import tech.lapsa.esbd.dao.elements.IElementsService;
import tech.lapsa.esbd.dao.elements.dict.IDictElementsService.IDictElementsServiceLocal;
import tech.lapsa.esbd.dao.elements.dict.IDictElementsService.IDictElementsServiceRemote;

public interface CountryService extends IElementsService<Country> {

    public static final String BEAN_NAME = "CountryServiceBean";

    @Local
    public interface CountryServiceLocal extends IDictElementsServiceLocal<Country>, CountryService {
    }

    @Remote
    public interface CountryServiceRemote extends IDictElementsServiceRemote<Country>, CountryService {
    }
}
