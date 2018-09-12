package tech.lapsa.esbd.dao.entities.complex;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.IPreloadedEntitiesService;
import tech.lapsa.esbd.domain.complex.UserEntity;

public interface UserEntityService extends IPreloadedEntitiesService<UserEntity> {

    public static final String BEAN_NAME = "UserEntityServiceBean";

    @Local
    public interface UserEntityServiceLocal
	    extends IPreloadedEntitiesServiceLocal<UserEntity>, UserEntityService {
    }

    @Remote
    public interface UserEntityServiceRemote
	    extends IPreloadedEntitiesServiceRemote<UserEntity>, UserEntityService {
    }
    
    UserEntity currentUser();
}
