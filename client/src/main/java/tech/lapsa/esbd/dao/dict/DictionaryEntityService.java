package tech.lapsa.esbd.dao.dict;

import java.util.List;

import tech.lapsa.esbd.dao.GeneralService;

public interface DictionaryEntityService<T extends DictionaryEntity<I>, I extends Number> extends GeneralService<T, I> {

    List<T> getAll();
}
