package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.KZAreaMapping;
import tech.lapsa.insurance.esbd.elements.KZAreaService.KZAreaServiceLocal;
import test.ArquillianBaseTestCase;

public class CountryRegionServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZAreaServiceLocal service;

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
