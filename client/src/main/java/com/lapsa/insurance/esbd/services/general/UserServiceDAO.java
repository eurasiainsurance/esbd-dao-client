package com.lapsa.insurance.esbd.services.general;

import java.util.List;

import com.lapsa.insurance.esbd.domain.entities.general.UserEntity;
import com.lapsa.insurance.esbd.services.GeneralServiceDAO;

public interface UserServiceDAO extends GeneralServiceDAO<UserEntity, Long> {
    List<UserEntity> getAll();
}
