package tech.lapsa.esbd.dao.entities.dict;

import java.util.List;

import tech.lapsa.esbd.dao.entities.IPreloadedEntitiesService;
import tech.lapsa.esbd.domain.dict.ADictEntity;

public interface IDictEntitiesService<T extends ADictEntity> extends IPreloadedEntitiesService<T> {

    public interface ADictEntityServiceLocal<T extends ADictEntity>
	    extends IPreloadedEntitiesServiceLocal<T>, IDictEntitiesService<T> {
    }

    public interface ADictEntityServiceRemote<T extends ADictEntity>
	    extends IPreloadedEntitiesServiceRemote<T>, IDictEntitiesService<T> {
    }

    List<T> getAll();
}
