package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.ICachableEntitiesService;
import tech.lapsa.esbd.domain.complex.VehicleManufacturerEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface VehicleManufacturerEntityService extends ICachableEntitiesService<VehicleManufacturerEntity> {

    public static final String BEAN_NAME = "VehicleManufacturerEntityServiceBean";

    @Local
    public interface VehicleManufacturerEntityServiceLocal
	    extends ICachableEntityServiceLocal<VehicleManufacturerEntity>, VehicleManufacturerEntityService {
    }

    @Remote
    public interface VehicleManufacturerEntityServiceRemote
	    extends ICachableEntityServiceRemote<VehicleManufacturerEntity>, VehicleManufacturerEntityService {
    }

    List<VehicleManufacturerEntity> getByName(String name) throws IllegalArgument;
}
