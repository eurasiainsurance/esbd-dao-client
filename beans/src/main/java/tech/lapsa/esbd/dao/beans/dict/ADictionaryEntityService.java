package tech.lapsa.esbd.dao.beans.dict;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.ejb.EJB;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.dict.DictionaryEntity;
import tech.lapsa.esbd.dao.dict.DictionaryEntityService;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfItem;
import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;

public abstract class ADictionaryEntityService<T extends DictionaryEntity<I>, I extends Number>
	implements DictionaryEntityService<T, I> {

    private final String dictionaryName;
    private final Function<Item, T> converter;

    ADictionaryEntityService(final String dictionaryName, final Function<Item, T> converter) {
	this.dictionaryName = dictionaryName;
	this.converter = converter;
    }

    @EJB
    private ConnectionPool pool;

    private List<T> all;

    @Override
    public List<T> getAll() {
	return MyOptionals.of(all)
		.orElseGet(() -> (all = getAllFromESBD()));
    }

    @Override
    public T getById(final I id) throws IllegalArgument, NotFound {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	return getAll().stream() //
		.filter(x -> MyNumbers.numbericEquals(id, x.getId())) //
		.findFirst()
		.orElseThrow(() -> new NotFound(String.format("Dictionary entity with id = '%1$s' is not found", id)));
    }

    // PRIVATE

    private List<T> getAllFromESBD() {
	try (Connection con = pool.getConnection()) {
	    final ArrayOfItem items = con.getItems(dictionaryName);
	    if (items == null)
		return Collections.unmodifiableList(Collections.emptyList());
	    return items.getItem().stream() //
		    .map(converter) //
		    .collect(MyCollectors.unmodifiableList());
	}
    }
}
