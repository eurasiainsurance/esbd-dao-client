package tech.lapsa.esbd.dao.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.GeneralService;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface VehicleManufacturerEntityService extends GeneralService<VehicleManufacturerEntity, Integer> {

    public static final String BEAN_NAME = "VehicleManufacturerEntityServiceBean";

    @Local
    public interface VehicleManufacturerEntityServiceLocal
	    extends GeneralServiceLocal<VehicleManufacturerEntity, Integer>, VehicleManufacturerEntityService {
    }

    @Remote
    public interface VehicleManufacturerEntityServiceRemote
	    extends GeneralServiceRemote<VehicleManufacturerEntity, Integer>, VehicleManufacturerEntityService {
    }

    List<VehicleManufacturerEntity> getByName(String name) throws IllegalArgument;
}
