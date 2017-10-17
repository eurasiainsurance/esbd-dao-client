package com.lapsa.insurance.esbd.services.policy;

import java.util.List;

import com.lapsa.insurance.esbd.domain.entities.policy.VehicleEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;

public interface VehicleServiceDAO extends GeneralServiceDAO<VehicleEntity, Long> {
    List<VehicleEntity> getByVINCode(String vinCode);

    List<VehicleEntity> getByRegNumber(String regNumber);
}
