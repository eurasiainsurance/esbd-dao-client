package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.IdentityCardType;

public interface IdentityCardTypeService extends IDictElementsService<IdentityCardType> {

    public static final String BEAN_NAME = "IdentityCardTypeServiceBean";

    @Local
    public interface IdentityCardTypeServiceLocal
	    extends IDictElementsServiceLocal<IdentityCardType>, IdentityCardTypeService {
    }

    @Remote
    public interface IdentityCardTypeServiceRemote
	    extends IDictElementsServiceRemote<IdentityCardType>, IdentityCardTypeService {
    }
}
