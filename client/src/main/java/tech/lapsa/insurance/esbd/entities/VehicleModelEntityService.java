package com.lapsa.insurance.esbd.services.policy;

import java.util.List;

import com.lapsa.insurance.esbd.domain.entities.policy.VehicleManufacturerEntity;
import com.lapsa.insurance.esbd.domain.entities.policy.VehicleModelEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;

public interface VehicleModelServiceDAO extends GeneralServiceDAO<VehicleModelEntity, Long> {
    List<VehicleModelEntity> getByName(String name);

    List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer);
}
