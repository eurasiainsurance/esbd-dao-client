package tech.lapsa.esbd.dao;

import tech.lapsa.java.commons.exceptions.IllegalArgument;

public interface GeneralService<T, I> extends EJBConstants {

    T getById(I id) throws IllegalArgument, NotFound;
}
