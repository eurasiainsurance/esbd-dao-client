package test.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.esbd.domain.entities.policy.VehicleManufacturerEntity;
import com.lapsa.insurance.esbd.domain.entities.policy.VehicleModelEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.policy.VehicleModelServiceDAO;

import test.ArquillianBaseTestCase;

public class VehicleModelServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private VehicleModelServiceDAO service;

    @Test
    public void testGetById() throws NamingException {
	try {
	    VehicleModelEntity res = service.getById(VALID_VEHICLE_MODEL_ID);
	    assertThat(res, not(nullValue()));
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(ININVALID_VEHICLE_MODEL_ID);
    }

    @Test
    public void testGetByName() throws NamingException {
	List<VehicleModelEntity> list = service.getByName(VALID_VEHICLE_MODEL_NAME);
	assertThat(list, allOf(not(nullValue()), not(empty())));
	for (VehicleModelEntity e : list) {
	    assertThat(e, not(nullValue()));
	}
    }

    @Test
    public void testGetByManufacturer() throws NamingException, NotFound {
	VehicleManufacturerEntity validManufacturer = new VehicleManufacturerEntity();
	validManufacturer.setId(VALID_VEHICLE_MANUFACTURER_ID);
	List<VehicleModelEntity> list = service.getByManufacturer(validManufacturer);
	assertThat(list, allOf(not(nullValue()), not(empty())));
	for (VehicleModelEntity e : list) {
	    assertThat(e, not(nullValue()));
	}
    }
}
