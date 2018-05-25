package tech.lapsa.esbd.dao.elements;

import tech.lapsa.esbd.dao.IService;

public interface IElementsService<T extends Enum<T>> extends IService<T, Integer> {

    public interface IElementsServiceLocal<T extends Enum<T>>
	    extends IServiceLocal<T, Integer>, IElementsService<T> {
    }

    public interface IElementsServiceRemote<T extends Enum<T>>
	    extends IServiceRemote<T, Integer>, IElementsService<T> {
    }
}
