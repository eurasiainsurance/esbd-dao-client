package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.MaritalStatus;

public interface MaritalStatusService extends ElementsService<MaritalStatus> {

    public static final String BEAN_NAME = "MaritalStatusServiceBean";

    @Local
    public interface MaritalStatusServiceLocal
	    extends ElementsServiceLocal<MaritalStatus>, MaritalStatusService {
    }

    @Remote
    public interface MaritalStatusServiceRemote
	    extends ElementsServiceRemote<MaritalStatus>, MaritalStatusService {
    }

}
