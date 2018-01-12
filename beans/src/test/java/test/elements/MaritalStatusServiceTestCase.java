package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.esbd.beans.dao.elements.mapping.MaritalStatusMapping;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.MaritalStatusService.MaritalStatusServiceLocal;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class MaritalStatusServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private MaritalStatusServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final int id : MaritalStatusMapping.getInstance().getAllIds())
	    try {
		final MaritalStatus res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(MaritalStatusMapping.getInstance().forId(id))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_MARITAL_STATUS_ID);
    }

}
