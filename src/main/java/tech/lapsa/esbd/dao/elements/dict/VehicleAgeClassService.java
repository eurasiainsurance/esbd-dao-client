package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleAgeClass;

import tech.lapsa.esbd.dao.elements.ElementsService;

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