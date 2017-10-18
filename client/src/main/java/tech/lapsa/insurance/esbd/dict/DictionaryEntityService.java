package tech.lapsa.insurance.esbd.dict;

import java.util.List;

import tech.lapsa.insurance.esbd.GeneralService;

public interface DictionaryEntityService<T extends DictionaryEntity<I>, I extends Number> extends GeneralService<T, I> {

    List<T> getAll();
}
