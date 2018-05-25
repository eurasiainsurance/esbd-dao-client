package tech.lapsa.esbd.dao.entities.dict;

import java.util.List;

import tech.lapsa.esbd.dao.entities.IEntitiesService;
import tech.lapsa.esbd.domain.dict.ADictEntity;

public interface IDictEntitiesService<T extends ADictEntity> extends IEntitiesService<T> {

    public interface ADictEntityServiceLocal<T extends ADictEntity>
	    extends IEntityServiceLocal<T>, IDictEntitiesService<T> {
    }

    public interface ADictEntityServiceRemote<T extends ADictEntity>
	    extends IEntityServiceRemote<T>, IDictEntitiesService<T> {
    }

    List<T> getAll();
}
