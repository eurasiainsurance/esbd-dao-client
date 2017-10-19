package tech.lapsa.insurance.esbd.beans.dict;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfItem;
import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.dict.DictionaryEntity;
import tech.lapsa.insurance.esbd.dict.DictionaryEntityService;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;

public abstract class ADictionaryEntityService<T extends DictionaryEntity<I>, I extends Number>
	implements DictionaryEntityService<T, I> {

    private final String dictionaryName;
    private final Function<Item, T> converter;

    ADictionaryEntityService(String dictionaryName, Function<Item, T> converter) {
	this.dictionaryName = dictionaryName;
	this.converter = converter;
    }

    @Inject
    private ConnectionPool pool;

    private List<T> all;

    @Override
    public List<T> getAll() {
	return MyOptionals.of(all)
		.orElseGet(() -> (all = getAllFromESBD()));
    }

    @Override
    public T getById(I id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	return getAll().stream() //
		.filter(x -> MyNumbers.numbericEquals(id, x.getId())) //
		.findFirst()
		.orElseThrow(() -> new NotFound(String.format("Dictionary entity with id = '%1$s' is not found", id)));
    }

    // PRIVATE

    private List<T> getAllFromESBD() {
	try (Connection con = pool.getConnection()) {
	    ArrayOfItem items = con.getItems(dictionaryName);
	    if (items == null)
		return Collections.unmodifiableList(Collections.emptyList());
	    return items.getItem().stream() //
		    .map(converter) //
		    .collect(MyCollectors.unmodifiableList());
	}
    }
}
