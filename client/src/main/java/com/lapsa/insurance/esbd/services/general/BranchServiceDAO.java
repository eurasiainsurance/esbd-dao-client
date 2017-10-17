package com.lapsa.insurance.esbd.services.general;

import java.util.List;

import com.lapsa.insurance.esbd.domain.entities.general.BranchEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;

public interface BranchServiceDAO extends GeneralServiceDAO<BranchEntity, Long> {
    List<BranchEntity> getAll();
}
