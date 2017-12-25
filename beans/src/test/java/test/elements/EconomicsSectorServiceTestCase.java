package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.beans.elements.mapping.KZEconomicSectorMapping;
import tech.lapsa.esbd.dao.elements.KZEconomicSectorService.KZEconomicSectorServiceLocal;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class EconomicsSectorServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZEconomicSectorServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final int i : KZEconomicSectorMapping.getInstance().getAllIds())
	    try {
		final KZEconomicSector res = service.getById(i);
		assertThat(res, allOf(not(nullValue()), equalTo(KZEconomicSectorMapping.getInstance().forId(i))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_ECONOMICS_SECTOR_ID);
    }

}
