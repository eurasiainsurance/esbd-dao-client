package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.IService;
import tech.lapsa.esbd.domain.AEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface IEntitiesService<DOMAIN extends AEntity> extends IService<DOMAIN, Integer> {

    public interface IEntitiesServiceLocal<DOMAIN extends AEntity>
	    extends IServiceLocal<DOMAIN, Integer>, IEntitiesService<DOMAIN> {
    }

    public interface IEntitiesServiceRemote<DOMAIN extends AEntity>
	    extends IServiceRemote<DOMAIN, Integer>, IEntitiesService<DOMAIN> {
    }

    DOMAIN save(DOMAIN entity) throws IllegalArgument;
}
