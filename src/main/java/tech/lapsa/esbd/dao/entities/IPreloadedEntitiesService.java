package tech.lapsa.esbd.dao.entities;

import java.util.List;

import tech.lapsa.esbd.domain.AEntity;

public interface IPreloadedEntitiesService<T extends AEntity> extends IEntitiesService<T> {

    public interface IPreloadedEntitiesServiceLocal<DOMAIN extends AEntity>
	    extends IEntitiesServiceLocal<DOMAIN>, IPreloadedEntitiesService<DOMAIN> {
    }

    public interface IPreloadedEntitiesServiceRemote<T extends AEntity>
	    extends IEntitiesServiceRemote<T>, IPreloadedEntitiesService<T> {
    }

    List<T> getAll();
}
