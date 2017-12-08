package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleAgeClass;

public interface VehicleAgeClassService extends ElementsService<VehicleAgeClass, Integer> {

    @Local
    public interface VehicleAgeClassServiceLocal extends VehicleAgeClassService {
    }

    @Remote
    public interface VehicleAgeClassServiceRemote extends VehicleAgeClassService {
    }
}