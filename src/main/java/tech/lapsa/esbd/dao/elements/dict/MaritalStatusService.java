package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.esbd.dao.IElementsService;

public interface MaritalStatusService extends IElementsService<MaritalStatus> {

    public static final String BEAN_NAME = "MaritalStatusServiceBean";

    @Local
    public interface MaritalStatusServiceLocal
	    extends IlementsServiceLocal<MaritalStatus>, MaritalStatusService {
    }

    @Remote
    public interface MaritalStatusServiceRemote
	    extends IlementsServiceRemote<MaritalStatus>, MaritalStatusService {
    }

}
