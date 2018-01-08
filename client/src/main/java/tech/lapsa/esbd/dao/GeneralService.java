package tech.lapsa.esbd.dao;

import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface GeneralService<T, I> extends EJBConstants {

    public interface GeneralServiceLocal<T, I> extends GeneralService<T, I> {
    }

    public interface GeneralServiceRemote<T, I> extends GeneralService<T, I> {
    }

    T getById(I id) throws IllegalArgument, NotFound;
}
