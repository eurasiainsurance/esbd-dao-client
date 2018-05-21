package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.AEntityService;
import tech.lapsa.esbd.domain.complex.UserEntity;

public interface UserEntityService extends AEntityService<UserEntity, Integer> {

    public static final String BEAN_NAME = "UserEntityServiceBean";

    @Local
    public interface UserEntityServiceLocal
	    extends AEntityServiceLocal<UserEntity, Integer>, UserEntityService {
    }

    @Remote
    public interface UserEntityServiceRemote
	    extends AEntityServiceRemote<UserEntity, Integer>, UserEntityService {
    }

    List<UserEntity> getAll();
}
