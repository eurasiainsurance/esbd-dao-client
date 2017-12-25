package tech.lapsa.esbd.dao.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.GeneralService;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface VehicleModelEntityService extends GeneralService<VehicleModelEntity, Integer> {

    public static final String BEAN_NAME = "VehicleModelEntityServiceBean";

    @Local
    public interface VehicleModelEntityServiceLocal extends VehicleModelEntityService {
    }

    @Remote
    public interface VehicleModelEntityServiceRemote extends VehicleModelEntityService {
    }

    List<VehicleModelEntity> getByName(String name) throws IllegalArgument;

    List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer) throws IllegalArgument;
}
