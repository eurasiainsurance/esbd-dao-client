package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.PersonType;

import tech.lapsa.esbd.dao.IElementsService;

public interface PersonTypeService extends IElementsService<PersonType> {

    public static final String BEAN_NAME = "PersonTypeServiceBean";

    @Local
    public interface PersonTypeServiceLocal
	    extends IlementsServiceLocal<PersonType>, PersonTypeService {
    }

    @Remote
    public interface PersonTypeServiceRemote
	    extends IlementsServiceRemote<PersonType>, PersonTypeService {
    }
}
