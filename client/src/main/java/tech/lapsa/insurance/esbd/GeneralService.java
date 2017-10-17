package com.lapsa.insurance.esbd.services;

public interface GeneralServiceDAO<T, I> {
    T getById(I id) throws NotFound;
}
