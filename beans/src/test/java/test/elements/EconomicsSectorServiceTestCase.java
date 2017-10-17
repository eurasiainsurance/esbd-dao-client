package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.KZEconomicSectorServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.KZEconomicSectorMapping;
import com.lapsa.kz.economic.KZEconomicSector;

import test.ArquillianBaseTestCase;

public class EconomicsSectorServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private KZEconomicSectorServiceDAO service;

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
