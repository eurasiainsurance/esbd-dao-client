package tech.lapsa.esbd.dao.entities.dict;

import java.util.List;

import tech.lapsa.esbd.dao.entities.AEntityService;

public interface ADictEntityService<T extends ADictEntity> extends AEntityService<T, Integer> {

    public interface ADictEntityServiceLocal<T extends ADictEntity>
	    extends AEntityServiceLocal<T, Integer>, ADictEntityService<T> {
    }

    public interface ADictEntityServiceRemote<T extends ADictEntity>
	    extends AEntityServiceRemote<T, Integer>, ADictEntityService<T> {
    }

    List<T> getAll();
}
