package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.KZEconomicSectorMapping;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService.KZEconomicSectorServiceLocal;
import test.ArquillianBaseTestCase;

public class EconomicsSectorServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZEconomicSectorServiceLocal service;

    @Test
    public void testGetById() throws NamingException {
	for (int i : KZEconomicSectorMapping.getInstance().getAllIds()) {
	    try {
		KZEconomicSector res = service.getById(i);
		assertThat(res, allOf(not(nullValue()), equalTo(KZEconomicSectorMapping.getInstance().forId(i))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_ECONOMICS_SECTOR_ID);
    }

}
