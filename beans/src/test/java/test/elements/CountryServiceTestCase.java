package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.international.country.Country;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.CountryMapping;
import tech.lapsa.insurance.esbd.elements.CountryService;
import test.ArquillianBaseTestCase;

public class CountryServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private CountryService service;

    @Test
    public void testGetById() throws NamingException {
	for (int i : CountryMapping.getInstance().getAllIds()) {
	    try {
		Country res = service.getById(i);
		assertThat(res, allOf(not(nullValue()), equalTo(CountryMapping.getInstance().forId(i))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_COUNTRY_ID);
    }

}
