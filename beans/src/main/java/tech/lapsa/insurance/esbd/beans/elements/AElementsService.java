package tech.lapsa.insurance.esbd.beans.elements;

import java.util.function.Function;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.elements.ElementsService;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;

public abstract class AElementsService<T extends Enum<T>, I extends Number> implements ElementsService<T, I> {

    private final Function<I, T> converter;

    AElementsService(final Function<I, T> converter) {
	this.converter = converter;
    }

    @Override
    public T getById(final I id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	return MyOptionals.of(converter.apply(id)) //
		.orElseThrow(() -> new NotFound(String.format("Element not found with id = '%1$s'", id)));
    }
}
