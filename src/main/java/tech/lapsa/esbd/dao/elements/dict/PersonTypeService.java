package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.PersonType;

public interface PersonTypeService extends IDictElementsService<PersonType> {

    public static final String BEAN_NAME = "PersonTypeServiceBean";

    @Local
    public interface PersonTypeServiceLocal
	    extends IDictElementsServiceLocal<PersonType>, PersonTypeService {
    }

    @Remote
    public interface PersonTypeServiceRemote
	    extends IDictElementsServiceRemote<PersonType>, PersonTypeService {
    }
}
