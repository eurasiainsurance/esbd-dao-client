package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.VehicleClassService.VehicleClassServiceLocal;
import tech.lapsa.esbd.dao.elements.mapping.VehicleClassMapping;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class VehicleClassServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private VehicleClassServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final int id : VehicleClassMapping.getInstance().getAllIds())
	    try {
		final VehicleClass res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(VehicleClassMapping.getInstance().forId(id))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_VEHICLE_CLASS_ID);
    }

}
