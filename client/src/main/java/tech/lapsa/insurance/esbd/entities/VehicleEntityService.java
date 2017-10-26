package tech.lapsa.insurance.esbd.entities;

import java.util.List;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

@Local
public interface VehicleEntityService extends GeneralService<VehicleEntity, Integer> {

    List<VehicleEntity> getByVINCode(String vinCode);

    List<VehicleEntity> getByRegNumber(VehicleRegNumber regNumber);
}
