package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.IEntitiesService;
import tech.lapsa.esbd.domain.complex.VehicleManufacturerEntity;
import tech.lapsa.esbd.domain.complex.VehicleModelEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface VehicleModelEntityService extends IEntitiesService<VehicleModelEntity> {

    public static final String BEAN_NAME = "VehicleModelEntityServiceBean";

    @Local
    public interface VehicleModelEntityServiceLocal
	    extends IEntityServiceLocal<VehicleModelEntity>, VehicleModelEntityService {
    }

    @Remote
    public interface VehicleModelEntityServiceRemote
	    extends IEntityServiceRemote<VehicleModelEntity>, VehicleModelEntityService {
    }

    List<VehicleModelEntity> getByName(String name) throws IllegalArgument;

    List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer) throws IllegalArgument;
}
