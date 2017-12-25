package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleAgeClass;

public interface VehicleAgeClassService extends ElementsService<VehicleAgeClass, Integer> {

    public static final String BEAN_NAME = "VehicleAgeClassServiceBean";

    @Local
    public interface VehicleAgeClassServiceLocal extends VehicleAgeClassService {
    }

    @Remote
    public interface VehicleAgeClassServiceRemote extends VehicleAgeClassService {
    }
}