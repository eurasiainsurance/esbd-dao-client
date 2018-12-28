package tech.lapsa.esbd.dao.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.domain.entities.UserEntity;

public interface UserEntityService extends GeneralEntityService<UserEntity, Integer> {

    public static final String BEAN_NAME = "UserEntityServiceBean";

    @Local
    public interface UserEntityServiceLocal
	    extends GeneralEntityServiceLocal<UserEntity, Integer>, UserEntityService {
    }

    @Remote
    public interface UserEntityServiceRemote
	    extends GeneralEntityServiceRemote<UserEntity, Integer>, UserEntityService {
    }

    List<UserEntity> getAll();
}
