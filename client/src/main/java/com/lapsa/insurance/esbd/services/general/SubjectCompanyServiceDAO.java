package com.lapsa.insurance.esbd.services.general;

import com.lapsa.insurance.esbd.domain.entities.general.SubjectCompanyEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;
import com.lapsa.insurance.esbd.services.NotFound;

public interface SubjectCompanyServiceDAO extends GeneralServiceDAO<SubjectCompanyEntity, Long> {
    SubjectCompanyEntity getByBIN(String bin) throws NotFound;
}
