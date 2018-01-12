package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.beans.dao.elements.mapping.KZAreaMapping;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.KZAreaService.KZAreaServiceLocal;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class CountryRegionServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZAreaServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final int i : KZAreaMapping.getInstance().getAllIds())
	    try {
		final KZArea res = service.getById(i);
		assertThat(res, allOf(not(nullValue()), equalTo(KZAreaMapping.getInstance().forId(i))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_COUNTRY_REGION_ID);
    }

}
