package tech.lapsa.esbd.dao.entities;

import tech.lapsa.esbd.dao.GeneralService;

public interface GeneralEntityService<T, I> extends GeneralService<T, I> {

    public interface GeneralEntityServiceLocal<T, I> extends GeneralServiceLocal<T, I>, GeneralEntityService<T, I> {
    }

    public interface GeneralEntityServiceRemote<T, I> extends GeneralServiceRemote<T, I>, GeneralEntityService<T, I> {
    }
}
