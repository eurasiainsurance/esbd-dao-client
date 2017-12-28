package tech.lapsa.esbd.dao.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.GeneralService;

public interface UserEntityService extends GeneralService<UserEntity, Integer> {

    public static final String BEAN_NAME = "UserEntityServiceBean";

    @Local
    public interface UserEntityServiceLocal extends UserEntityService {
    }

    @Remote
    public interface UserEntityServiceRemote extends UserEntityService {
    }

    List<UserEntity> getAll();
}