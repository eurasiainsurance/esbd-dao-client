package tech.lapsa.esbd.dao.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface VehicleModelEntityService extends GeneralEntityService<VehicleModelEntity, Integer> {

    public static final String BEAN_NAME = "VehicleModelEntityServiceBean";

    @Local
    public interface VehicleModelEntityServiceLocal
	    extends GeneralEntityServiceLocal<VehicleModelEntity, Integer>, VehicleModelEntityService {
    }

    @Remote
    public interface VehicleModelEntityServiceRemote
	    extends GeneralEntityServiceRemote<VehicleModelEntity, Integer>, VehicleModelEntityService {
    }

    List<VehicleModelEntity> getByName(String name) throws IllegalArgument;

    List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer) throws IllegalArgument;
}
