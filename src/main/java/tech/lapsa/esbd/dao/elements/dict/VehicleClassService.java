package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.elements.ElementsService;

public interface VehicleClassService extends ElementsService<VehicleClass> {

    public static final String BEAN_NAME = "VehicleClassServiceBean";

    @Local
    public interface VehicleClassServiceLocal extends ElementsServiceLocal<VehicleClass>, VehicleClassService {
    }

    @Remote
    public interface VehicleClassServiceRemote
	    extends ElementsServiceRemote<VehicleClass>, VehicleClassService {
    }
}
