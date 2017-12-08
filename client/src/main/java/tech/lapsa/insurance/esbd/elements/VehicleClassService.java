package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleClass;

public interface VehicleClassService extends ElementsService<VehicleClass, Integer> {

    @Local
    public interface VehicleClassServiceLocal extends VehicleClassService {
    }

    @Remote
    public interface VehicleClassServiceRemote extends VehicleClassService {
    }
}
