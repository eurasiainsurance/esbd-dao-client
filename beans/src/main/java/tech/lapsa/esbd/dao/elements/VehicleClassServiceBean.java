package tech.lapsa.esbd.dao.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.elements.VehicleClassService.VehicleClassServiceLocal;
import tech.lapsa.esbd.dao.elements.VehicleClassService.VehicleClassServiceRemote;
import tech.lapsa.esbd.dao.elements.mapping.VehicleClassMapping;

@Singleton(name = VehicleClassService.BEAN_NAME)
public class VehicleClassServiceBean extends AElementsService<VehicleClass, Integer>
	implements VehicleClassServiceLocal, VehicleClassServiceRemote {

    public VehicleClassServiceBean() {
	super(VehicleClassMapping.getInstance()::forId, VehicleClassService.class);
    }
}
