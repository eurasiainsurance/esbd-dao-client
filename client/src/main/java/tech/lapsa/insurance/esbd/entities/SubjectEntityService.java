package com.lapsa.insurance.esbd.services.general;

import com.lapsa.insurance.esbd.domain.entities.general.SubjectEntity;
import com.lapsa.insurance.esbd.services.NotFound;

public interface SubjectServiceDAO {
    SubjectEntity getById(Long id) throws NotFound;

    SubjectEntity getByIdNumber(String idNumber) throws NotFound;
}
