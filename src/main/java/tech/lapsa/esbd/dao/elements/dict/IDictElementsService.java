package tech.lapsa.esbd.dao.elements.dict;

import tech.lapsa.esbd.dao.elements.IElementsService;

public interface IDictElementsService<T extends Enum<T>> extends IElementsService<T> {

    public interface IDictElementsServiceLocal<T extends Enum<T>>
	    extends IElementsServiceLocal<T>, IDictElementsService<T> {
    }

    public interface IDictElementsServiceRemote<T extends Enum<T>>
	    extends IElementsServiceRemote<T>, IDictElementsService<T> {
    }
}
