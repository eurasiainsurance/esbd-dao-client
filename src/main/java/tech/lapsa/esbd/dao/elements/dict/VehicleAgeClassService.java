package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleAgeClass;

public interface VehicleAgeClassService extends IDictElementsService<VehicleAgeClass> {

    public static final String BEAN_NAME = "VehicleAgeClassServiceBean";

    @Local
    public interface VehicleAgeClassServiceLocal
	    extends IDictElementsServiceLocal<VehicleAgeClass>, VehicleAgeClassService {
    }

    @Remote
    public interface VehicleAgeClassServiceRemote
	    extends IDictElementsServiceRemote<VehicleAgeClass>, VehicleAgeClassService {
    }
}