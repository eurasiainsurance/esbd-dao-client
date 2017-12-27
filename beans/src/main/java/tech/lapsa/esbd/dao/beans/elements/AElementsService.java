package tech.lapsa.esbd.dao.beans.elements;

import java.util.function.Function;

import javax.ejb.EJBException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.ElementsService;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.logging.MyLogger;

public abstract class AElementsService<T extends Enum<T>, I extends Number> implements ElementsService<T, I> {

    private final Function<I, T> converter;
    private final MyLogger logger;

    AElementsService(final Function<I, T> converter, final Class<T> clazz) {
	this.converter = converter;
	this.logger = MyLogger.newBuilder() //
		.withNameOf(clazz) //
		.build();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T getById(final I id) throws NotFound, IllegalArgument {
	try {
	    return _getById(id);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    private T _getById(final I id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	return MyOptionals.of(converter.apply(id)) //
		.orElseThrow(() -> new NotFound(String.format("Element not found with id = '%1$s'", id)));
    }
}
