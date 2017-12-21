package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.insurance.elements.VehicleAgeClass;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.VehicleAgeClassMapping;
import tech.lapsa.insurance.esbd.elements.VehicleAgeClassService.VehicleAgeClassServiceLocal;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class VehicleAgeClassServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private VehicleAgeClassServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final Integer id : VehicleAgeClassMapping.getInstance().getAllIds())
	    try {
		final VehicleAgeClass res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(VehicleAgeClassMapping.getInstance().forId(id))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_VEHICLE_AGE_CLASS_ID);
    }

}
