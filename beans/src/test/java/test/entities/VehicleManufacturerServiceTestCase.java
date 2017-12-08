package test.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.VehicleManufacturerEntity;
import tech.lapsa.insurance.esbd.entities.VehicleManufacturerEntityService.VehicleManufacturerEntityServiceLocal;
import test.ArquillianBaseTestCase;

public class VehicleManufacturerServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private VehicleManufacturerEntityServiceLocal service;

    @Test
    public void testGetById() throws NamingException {
	try {
	    VehicleManufacturerEntity res = service.getById(VALID_VEHICLE_MANUFACTURER_ID);
	    assertThat(res, not(nullValue()));
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(ININVALID_VEHICLE_MANUFACTURER_ID);
    }

    @Test
    public void testGetByName() throws NamingException {
	List<VehicleManufacturerEntity> list = service.getByName("INFINITI FX");
	assertThat(list, allOf(not(nullValue()), not(empty())));
	for (VehicleManufacturerEntity e : list) {
	    assertThat(e, not(nullValue()));
	}
    }

    @Test
    public void testGetByName_empty() throws NamingException {
	List<VehicleManufacturerEntity> list = service.getByName(INVALID_VEHICLE_MANUFACTURER_NAME);
	// TODO DEBUG : Need to debug
	assertThat(list, allOf(not(nullValue()), not(empty())));
	for (VehicleManufacturerEntity e : list) {
	    assertThat(e, not(nullValue()));
	}
    }

}
