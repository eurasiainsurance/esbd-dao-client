package tech.lapsa.esbd.dao.elements;

import tech.lapsa.esbd.dao.GeneralService;

public interface ElementsService<T extends Enum<T>, I extends Number> extends GeneralService<T, I> {

    public interface ElementsServiceLocal<T extends Enum<T>, I extends Number>
	    extends GeneralServiceLocal<T, I>, ElementsService<T, I> {
    }

    public interface ElementsServiceRemote<T extends Enum<T>, I extends Number>
	    extends GeneralServiceRemote<T, I>, ElementsService<T, I> {
    }
}
