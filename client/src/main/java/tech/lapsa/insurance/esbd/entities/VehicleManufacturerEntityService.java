package tech.lapsa.insurance.esbd.entities;

import java.util.List;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;

@Local
public interface VehicleManufacturerEntityService extends GeneralService<VehicleManufacturerEntity, Integer> {

    List<VehicleManufacturerEntity> getByName(String name);
}
