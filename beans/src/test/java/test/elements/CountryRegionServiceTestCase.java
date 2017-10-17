package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.KZAreaServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.KZAreaMapping;
import com.lapsa.kz.country.KZArea;

import test.ArquillianBaseTestCase;

public class CountryRegionServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZAreaServiceDAO service;

    @Test
    public void testGetById() throws NamingException {
	for (int i : KZAreaMapping.getInstance().getAllIds()) {
	    try {
		KZArea res = service.getById(i);
		assertThat(res, allOf(not(nullValue()), equalTo(KZAreaMapping.getInstance().forId(i))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_COUNTRY_REGION_ID);
    }

}
