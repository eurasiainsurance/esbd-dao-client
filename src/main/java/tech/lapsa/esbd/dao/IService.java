package tech.lapsa.esbd.dao;

import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface IService<T, I> extends EJBConstants {

    public interface IServiceLocal<T, I> extends IService<T, I> {
    }

    public interface IServiceRemote<T, I> extends IService<T, I> {
    }

    T getById(I id) throws IllegalArgument, NotFound;
}
