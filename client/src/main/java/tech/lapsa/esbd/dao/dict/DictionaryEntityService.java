package tech.lapsa.esbd.dao.dict;

import java.util.List;

import tech.lapsa.esbd.dao.GeneralService;

public interface DictionaryEntityService<T extends DictionaryEntity> extends GeneralService<T, Integer> {

    public interface DictionaryEntityServiceLocal<T extends DictionaryEntity>
	    extends GeneralServiceLocal<T, Integer>, DictionaryEntityService<T> {
    }

    public interface DictionaryEntityServiceRemote<T extends DictionaryEntity>
	    extends GeneralServiceRemote<T, Integer>, DictionaryEntityService<T> {
    }

    List<T> getAll();
}
