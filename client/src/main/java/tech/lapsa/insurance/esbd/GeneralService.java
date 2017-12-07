package tech.lapsa.insurance.esbd;

public interface GeneralService<T, I> {

    T getById(I id) throws NotFound;
}
