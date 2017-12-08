package tech.lapsa.insurance.esbd.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

public interface VehicleEntityService extends GeneralService<VehicleEntity, Integer> {

    @Local
    public interface VehicleEntityServiceLocal extends VehicleEntityService {
    }

    @Remote
    public interface VehicleEntityServiceRemote extends VehicleEntityService {
    }

    List<VehicleEntity> getByVINCode(String vinCode);

    List<VehicleEntity> getByRegNumber(VehicleRegNumber regNumber);
}
