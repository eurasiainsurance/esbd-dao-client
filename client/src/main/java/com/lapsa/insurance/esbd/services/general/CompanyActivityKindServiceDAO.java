package com.lapsa.insurance.esbd.services.general;

import java.util.List;

import com.lapsa.insurance.esbd.domain.entities.general.CompanyActivityKindEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;

public interface CompanyActivityKindServiceDAO extends GeneralServiceDAO<CompanyActivityKindEntity, Long> {
    List<CompanyActivityKindEntity> getAll();
}
