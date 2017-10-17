package com.lapsa.insurance.esbd.services.general;

import com.lapsa.insurance.esbd.domain.entities.general.SubjectPersonEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;
import com.lapsa.insurance.esbd.services.NotFound;

public interface SubjectPersonServiceDAO extends GeneralServiceDAO<SubjectPersonEntity, Long> {
    SubjectPersonEntity getByIIN(String iin) throws NotFound;
}
