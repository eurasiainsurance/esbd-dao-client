package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.PersonType;

import tech.lapsa.esbd.dao.elements.ElementsService;

public interface PersonTypeService extends ElementsService<PersonType> {

    public static final String BEAN_NAME = "PersonTypeServiceBean";

    @Local
    public interface PersonTypeServiceLocal
	    extends ElementsServiceLocal<PersonType>, PersonTypeService {
    }

    @Remote
    public interface PersonTypeServiceRemote
	    extends ElementsServiceRemote<PersonType>, PersonTypeService {
    }
}
