package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.elements.VehicleAgeClass;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.VehicleAgeClassMapping;
import tech.lapsa.insurance.esbd.elements.VehicleAgeClassService;
import test.ArquillianBaseTestCase;

public class VehicleAgeClassServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private VehicleAgeClassService service;

    @Test
    public void testGetById() throws NamingException {
	for (Integer id : VehicleAgeClassMapping.getInstance().getAllIds()) {
	    try {
		VehicleAgeClass res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(VehicleAgeClassMapping.getInstance().forId(id))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_VEHICLE_AGE_CLASS_ID);
    }

}
