package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.IElementsService;

public interface VehicleClassService extends IElementsService<VehicleClass> {

    public static final String BEAN_NAME = "VehicleClassServiceBean";

    @Local
    public interface VehicleClassServiceLocal extends IlementsServiceLocal<VehicleClass>, VehicleClassService {
    }

    @Remote
    public interface VehicleClassServiceRemote
	    extends IlementsServiceRemote<VehicleClass>, VehicleClassService {
    }
}
