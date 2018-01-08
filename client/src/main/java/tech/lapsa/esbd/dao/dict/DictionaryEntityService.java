package tech.lapsa.esbd.dao.dict;

import java.util.List;

import tech.lapsa.esbd.dao.GeneralService;

public interface DictionaryEntityService<T extends DictionaryEntity<I>, I extends Number> extends GeneralService<T, I> {

    public interface DictionaryEntityServiceLocal<T extends DictionaryEntity<I>, I extends Number>
	    extends GeneralServiceLocal<T, I>, DictionaryEntityService<T, I> {
    }

    public interface DictionaryEntityServiceRemote<T extends DictionaryEntity<I>, I extends Number>
	    extends GeneralServiceRemote<T, I>, DictionaryEntityService<T, I> {
    }

    List<T> getAll();
}
