package tech.lapsa.insurance.esbd.entities;

import java.util.List;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;

@Local
public interface VehicleModelEntityService extends GeneralService<VehicleModelEntity, Integer> {

    List<VehicleModelEntity> getByName(String name);

    List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer);
}
