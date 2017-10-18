package tech.lapsa.insurance.esbd;

import java.util.Optional;

public interface GeneralService<T, I> {

    T getById(I id) throws NotFound;

    default Optional<T> optionalById(I id) {
	try {
	    return Optional.of(getById(id));
	} catch (NotFound e) {
	    return Optional.empty();
	}
    }
}
