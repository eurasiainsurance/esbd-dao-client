package tech.lapsa.esbd.dao.beans.entities;

import java.util.List;
import java.util.function.Consumer;

import javax.ejb.EJBException;

import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.function.MyStrings;

final class Util {

    private Util() {
    }

    static <T> T requireSingle(final List<T> list,
	    final Class<?> clazz,
	    final String keyName,
	    final Object key) throws EJBException {
	if (list.size() > 1)
	    throw MyExceptions.format(EJBException::new, "Too many %1$s (%2$s) with %3$s = '%4$s'",
		    clazz.getSimpleName(), // 1
		    list.size(), // 2
		    keyName, // 3
		    key // 4
	    );
	return list.get(0);
    }

    @FunctionalInterface
    public interface ThrowingFunction<T, R> {
	R apply(T value) throws Exception;
    }

    static <T, F, FI> void requireField(final T target,
	    final Object targetId,
	    final ThrowingFunction<FI, F> fieldGeter,
	    final Consumer<F> fieldSeter,
	    final String fieldName,
	    final Class<F> fieldClazz,
	    final FI fieldId) {
	final F fieldObject;
	try {
	    fieldObject = fieldGeter.apply(fieldId);
	} catch (final Exception e) {
	    final String message = MyStrings.format(
		    "Error while fetching %1$s ID = '%2$s' from ESBD. %3$s (%4$s ID=%5$s) not found",
		    target.getClass(), // 1,
		    targetId, // 2
		    fieldName, // 3
		    fieldClazz.getSimpleName(), // 4
		    fieldId // 5
	    );
	    throw new EJBException(message, e);
	}
	fieldSeter.accept(fieldObject);
    }

    static <T> EJBException requireNonEmtyList(final T target,
	    final Object targetId,
	    final String fieldName) {
	final String message = MyStrings.format(
		"Error while fetching %1$s ID = '%2$s' from ESBD. %3$s list is empty",
		target.getClass(), // 1,
		targetId, // 2
		fieldName // 3
	);
	return new EJBException(message);
    }
}
