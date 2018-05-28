package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.domain.AEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface ICachableEntitiesService<DOMAIN extends AEntity> extends IEntitiesService<DOMAIN> {

    public interface ICachableEntityServiceLocal<DOMAIN extends AEntity>
	    extends IEntitiesServiceLocal<DOMAIN>, ICachableEntitiesService<DOMAIN> {
    }

    public interface ICachableEntityServiceRemote<DOMAIN extends AEntity>
	    extends IEntitiesServiceRemote<DOMAIN>, ICachableEntitiesService<DOMAIN> {
    }

    DOMAIN getByIdBypassCache(Integer id) throws IllegalArgument, NotFound;
}
