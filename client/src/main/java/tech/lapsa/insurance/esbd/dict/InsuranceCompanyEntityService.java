package com.lapsa.insurance.esbd.services.general;

import java.util.List;

import com.lapsa.insurance.esbd.domain.entities.general.InsuranceCompanyEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;

public interface InsuranceCompanyServiceDAO extends GeneralServiceDAO<InsuranceCompanyEntity, Long> {
    List<InsuranceCompanyEntity> getAll();
}
