package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleAgeClass;

public interface VehicleAgeClassService extends ElementsService<VehicleAgeClass> {

    public static final String BEAN_NAME = "VehicleAgeClassServiceBean";

    @Local
    public interface VehicleAgeClassServiceLocal
	    extends ElementsServiceLocal<VehicleAgeClass>, VehicleAgeClassService {
    }

    @Remote
    public interface VehicleAgeClassServiceRemote
	    extends ElementsServiceRemote<VehicleAgeClass>, VehicleAgeClassService {
    }
}