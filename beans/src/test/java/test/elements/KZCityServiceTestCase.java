package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.KZCityMapping;
import tech.lapsa.insurance.esbd.elements.KZCityService.KZCityServiceLocal;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class KZCityServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZCityServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final int i : KZCityMapping.getInstance().getAllIds())
	    try {
		final KZCity res = service.getById(i);
		assertThat(res, allOf(not(nullValue()), equalTo(KZCityMapping.getInstance().forId(i))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_KZ_CITY_ID);
    }

}
