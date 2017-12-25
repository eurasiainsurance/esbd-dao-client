package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleClass;

public interface VehicleClassService extends ElementsService<VehicleClass, Integer> {

    public static final String BEAN_NAME = "VehicleClassServiceBean";

    @Local
    public interface VehicleClassServiceLocal extends VehicleClassService {
    }

    @Remote
    public interface VehicleClassServiceRemote extends VehicleClassService {
    }
}
