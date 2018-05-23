package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.esbd.dao.IElementsService;

public interface IdentityCardTypeService extends IElementsService<IdentityCardType> {

    public static final String BEAN_NAME = "IdentityCardTypeServiceBean";

    @Local
    public interface IdentityCardTypeServiceLocal
	    extends IlementsServiceLocal<IdentityCardType>, IdentityCardTypeService {
    }

    @Remote
    public interface IdentityCardTypeServiceRemote
	    extends IlementsServiceRemote<IdentityCardType>, IdentityCardTypeService {
    }
}
