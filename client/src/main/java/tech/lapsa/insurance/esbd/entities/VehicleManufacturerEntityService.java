package tech.lapsa.insurance.esbd.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.insurance.esbd.GeneralService;

public interface VehicleManufacturerEntityService extends GeneralService<VehicleManufacturerEntity, Integer> {

    @Local
    public interface VehicleManufacturerEntityServiceLocal extends VehicleManufacturerEntityService {
    }

    @Remote
    public interface VehicleManufacturerEntityServiceRemote extends VehicleManufacturerEntityService {
    }

    List<VehicleManufacturerEntity> getByName(String name);
}
