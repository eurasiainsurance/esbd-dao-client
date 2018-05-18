package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.AEntityService;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface VehicleModelEntityService extends AEntityService<VehicleModelEntity, Integer> {

    public static final String BEAN_NAME = "VehicleModelEntityServiceBean";

    @Local
    public interface VehicleModelEntityServiceLocal
	    extends AEntityServiceLocal<VehicleModelEntity, Integer>, VehicleModelEntityService {
    }

    @Remote
    public interface VehicleModelEntityServiceRemote
	    extends AEntityServiceRemote<VehicleModelEntity, Integer>, VehicleModelEntityService {
    }

    List<VehicleModelEntity> getByName(String name) throws IllegalArgument;

    List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer) throws IllegalArgument;
}
