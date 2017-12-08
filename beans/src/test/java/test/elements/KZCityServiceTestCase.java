package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.KZCityMapping;
import tech.lapsa.insurance.esbd.elements.KZCityService;
import test.ArquillianBaseTestCase;

public class KZCityServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZCityService service;

    @Test
    public void testGetById() throws NamingException {
	for (int i : KZCityMapping.getInstance().getAllIds()) {
	    try {
		KZCity res = service.getById(i);
		assertThat(res, allOf(not(nullValue()), equalTo(KZCityMapping.getInstance().forId(i))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_KZ_CITY_ID);
    }

}
