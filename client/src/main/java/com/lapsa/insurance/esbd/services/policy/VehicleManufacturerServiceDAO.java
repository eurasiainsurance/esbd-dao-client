package com.lapsa.insurance.esbd.services.policy;

import java.util.List;

import com.lapsa.insurance.esbd.domain.entities.policy.VehicleManufacturerEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;

public interface VehicleManufacturerServiceDAO extends GeneralServiceDAO<VehicleManufacturerEntity, Long> {
    List<VehicleManufacturerEntity> getByName(String name);
}
