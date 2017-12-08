package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.MaritalStatusMapping;
import tech.lapsa.insurance.esbd.elements.MaritalStatusService.MaritalStatusServiceLocal;
import test.ArquillianBaseTestCase;

public class MaritalStatusServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private MaritalStatusServiceLocal service;

    @Test
    public void testGetById() throws NamingException {
	for (int id : MaritalStatusMapping.getInstance().getAllIds()) {
	    try {
		MaritalStatus res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(MaritalStatusMapping.getInstance().forId(id))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_MARITAL_STATUS_ID);
    }

}
