package tech.lapsa.esbd.dao;

import tech.lapsa.esbd.domain.AEntity;

public interface IEntitiesService<DOMAIN extends AEntity> extends IService<DOMAIN, Integer> {

    public interface IEntityServiceLocal<DOMAIN extends AEntity>
	    extends IServiceLocal<DOMAIN, Integer>, IEntitiesService<DOMAIN> {
    }

    public interface IEntityServiceRemote<DOMAIN extends AEntity>
	    extends IServiceRemote<DOMAIN, Integer>, IEntitiesService<DOMAIN> {
    }
}
