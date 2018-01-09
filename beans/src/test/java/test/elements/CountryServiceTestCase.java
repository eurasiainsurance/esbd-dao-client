package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.international.country.Country;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.CountryService.CountryServiceLocal;
import tech.lapsa.esbd.dao.elements.mapping.CountryMapping;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class CountryServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private CountryServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final int i : CountryMapping.getInstance().getAllIds())
	    try {
		final Country res = service.getById(i);
		assertThat(res, allOf(not(nullValue()), equalTo(CountryMapping.getInstance().forId(i))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_COUNTRY_ID);
    }

}
