package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.VehicleClassMapping;
import tech.lapsa.insurance.esbd.elements.VehicleClassService.VehicleClassServiceLocal;
import test.ArquillianBaseTestCase;

public class VehicleClassServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private VehicleClassServiceLocal service;

    @Test
    public void testGetById() throws NamingException {
	for (int id : VehicleClassMapping.getInstance().getAllIds()) {
	    try {
		VehicleClass res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(VehicleClassMapping.getInstance().forId(id))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_VEHICLE_CLASS_ID);
    }

}
