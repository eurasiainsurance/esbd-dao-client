package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.IdentityCardType;

public interface IdentityCardTypeService extends ElementsService<IdentityCardType> {

    public static final String BEAN_NAME = "IdentityCardTypeServiceBean";

    @Local
    public interface IdentityCardTypeServiceLocal
	    extends ElementsServiceLocal<IdentityCardType>, IdentityCardTypeService {
    }

    @Remote
    public interface IdentityCardTypeServiceRemote
	    extends ElementsServiceRemote<IdentityCardType>, IdentityCardTypeService {
    }
}
