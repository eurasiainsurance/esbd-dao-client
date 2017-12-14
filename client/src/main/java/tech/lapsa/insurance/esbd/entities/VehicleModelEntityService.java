package tech.lapsa.insurance.esbd.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface VehicleModelEntityService extends GeneralService<VehicleModelEntity, Integer> {

    @Local
    public interface VehicleModelEntityServiceLocal extends VehicleModelEntityService {
    }

    @Remote
    public interface VehicleModelEntityServiceRemote extends VehicleModelEntityService {
    }

    List<VehicleModelEntity> getByName(String name) throws IllegalArgument;

    List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer) throws IllegalArgument;
}
