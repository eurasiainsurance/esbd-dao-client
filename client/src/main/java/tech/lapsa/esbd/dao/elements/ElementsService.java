package tech.lapsa.esbd.dao.elements;

import tech.lapsa.esbd.dao.GeneralService;

public interface ElementsService<T extends Enum<T>> extends GeneralService<T, Integer> {

    public interface ElementsServiceLocal<T extends Enum<T>>
	    extends GeneralServiceLocal<T, Integer>, ElementsService<T> {
    }

    public interface ElementsServiceRemote<T extends Enum<T>>
	    extends GeneralServiceRemote<T, Integer>, ElementsService<T> {
    }
}
