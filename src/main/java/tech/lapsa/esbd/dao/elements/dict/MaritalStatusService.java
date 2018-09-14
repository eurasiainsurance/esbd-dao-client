package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.MaritalStatus;

public interface MaritalStatusService extends IDictElementsService<MaritalStatus> {

    public static final String BEAN_NAME = "MaritalStatusServiceBean";

    @Local
    public interface MaritalStatusServiceLocal
	    extends IDictElementsServiceLocal<MaritalStatus>, MaritalStatusService {
    }

    @Remote
    public interface MaritalStatusServiceRemote
	    extends IDictElementsServiceRemote<MaritalStatus>, MaritalStatusService {
    }

}
