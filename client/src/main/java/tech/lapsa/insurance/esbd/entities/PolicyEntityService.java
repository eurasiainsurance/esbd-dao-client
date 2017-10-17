package com.lapsa.insurance.esbd.services.policy;

import com.lapsa.insurance.esbd.domain.entities.policy.PolicyEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;
import com.lapsa.insurance.esbd.services.NotFound;

public interface PolicyServiceDAO extends GeneralServiceDAO<PolicyEntity, Long> {
    PolicyEntity getByNumber(String number) throws NotFound;
}
