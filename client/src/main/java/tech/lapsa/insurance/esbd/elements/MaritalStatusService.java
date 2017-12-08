package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.MaritalStatus;

public interface MaritalStatusService extends ElementsService<MaritalStatus, Integer> {

    @Local
    public interface MaritalStatusServiceLocal extends MaritalStatusService {
    }

    @Remote
    public interface MaritalStatusServiceRemote extends MaritalStatusService {
    }

}
