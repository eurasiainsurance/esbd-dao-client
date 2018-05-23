package tech.lapsa.esbd.dao;

public interface IElementsService<T extends Enum<T>> extends IService<T, Integer> {

    public interface IlementsServiceLocal<T extends Enum<T>>
	    extends IServiceLocal<T, Integer>, IElementsService<T> {
    }

    public interface IlementsServiceRemote<T extends Enum<T>>
	    extends IServiceRemote<T, Integer>, IElementsService<T> {
    }
}
