package tech.lapsa.insurance.esbd.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.insurance.esbd.GeneralService;

public interface VehicleModelEntityService extends GeneralService<VehicleModelEntity, Integer> {

    @Local
    public interface VehicleModelEntityServiceLocal extends VehicleModelEntityService {
    }

    @Remote
    public interface VehicleModelEntityServiceRemote extends VehicleModelEntityService {
    }

    List<VehicleModelEntity> getByName(String name);

    List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer);
}
