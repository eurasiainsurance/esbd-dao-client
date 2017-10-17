package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.KZCityServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.KZCityMapping;
import com.lapsa.kz.country.KZCity;

import test.ArquillianBaseTestCase;

public class KZCityServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZCityServiceDAO service;

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
