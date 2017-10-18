package tech.lapsa.insurance.esbd.entities;

import java.util.List;

import javax.ejb.Local;

import tech.lapsa.insurance.esbd.GeneralService;

@Local
public interface UserEntityService extends GeneralService<UserEntity, Integer> {

    List<UserEntity> getAll();
}
