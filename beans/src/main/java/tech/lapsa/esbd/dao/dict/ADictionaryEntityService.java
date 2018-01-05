package tech.lapsa.esbd.dao.dict;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfItem;
import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.logging.MyLogger;

public abstract class ADictionaryEntityService<T extends DictionaryEntity<I>, I extends Number>
	implements DictionaryEntityService<T, I> {

    private final String dictionaryName;
    private final Function<Item, T> converter;
    private final MyLogger logger;

    public ADictionaryEntityService(final String dictionaryName, final Function<Item, T> converter,
	    final Class<?> clazz) {
	this.dictionaryName = dictionaryName;
	this.converter = converter;
	this.logger = MyLogger.newBuilder() //
		.withNameOf(clazz) //
		.build();
    }

    private Map<I, T> allMap;

    @PostConstruct
    public void loadDictionary() {
	final ArrayOfItem items;
	try (Connection con = pool.getConnection()) {
	    items = con.getItems(dictionaryName);
	}
	this.allMap = MyOptionals.of(items) //
		.map(ArrayOfItem::getItem) //
		.map(List::stream) //
		.orElseGet(Stream::empty) //
		.map(converter) //
		.collect(MyCollectors.unmodifiableMap(DictionaryEntity::getId, Function.identity()));

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> getAll() {
	try {
	    return allMap.entrySet() //
		    .stream() //
		    .map(Map.Entry::getValue) //
		    .collect(MyCollectors.unmodifiableList());
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T getById(final I id) throws IllegalArgument, NotFound {
	try {
	    return _getById(id);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    @EJB
    private ConnectionPool pool;

    private T _getById(final I id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	final T res = allMap.get(id);
	if (res == null)
	    throw new NotFound(String.format("Dictionary entity with id = '%1$s' is not found", id));
	return res;
    }
}
