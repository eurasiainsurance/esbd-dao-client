package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.insurance.esbd.beans.elements.mapping.VehicleClassMapping;
import tech.lapsa.insurance.esbd.elements.VehicleClassService;
import tech.lapsa.insurance.esbd.elements.VehicleClassService.VehicleClassServiceLocal;
import tech.lapsa.insurance.esbd.elements.VehicleClassService.VehicleClassServiceRemote;

@Singleton(name = VehicleClassService.BEAN_NAME)
public class VehicleClassServiceBean extends AElementsService<VehicleClass, Integer>
	implements VehicleClassServiceLocal, VehicleClassServiceRemote {

    public VehicleClassServiceBean() {
	super(VehicleClassMapping.getInstance()::forId);
    }
}
