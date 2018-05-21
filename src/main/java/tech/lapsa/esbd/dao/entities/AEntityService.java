package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.GeneralService;
import tech.lapsa.esbd.domain.AEntity;

public interface AEntityService<T extends AEntity, I> extends GeneralService<T, I> {

    public interface AEntityServiceLocal<T extends AEntity, I>
	    extends GeneralServiceLocal<T, I>, AEntityService<T, I> {
    }

    public interface AEntityServiceRemote<T extends AEntity, I>
	    extends GeneralServiceRemote<T, I>, AEntityService<T, I> {
    }
}
