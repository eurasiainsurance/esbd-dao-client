package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleClass;

public interface VehicleClassService extends IDictElementsService<VehicleClass> {

    public static final String BEAN_NAME = "VehicleClassServiceBean";

    @Local
    public interface VehicleClassServiceLocal extends IDictElementsServiceLocal<VehicleClass>, VehicleClassService {
    }

    @Remote
    public interface VehicleClassServiceRemote extends IDictElementsServiceRemote<VehicleClass>, VehicleClassService {
    }
}
