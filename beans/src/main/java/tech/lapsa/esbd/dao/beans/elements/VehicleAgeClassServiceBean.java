package tech.lapsa.esbd.dao.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.VehicleAgeClass;

import tech.lapsa.esbd.dao.beans.elements.mapping.VehicleAgeClassMapping;
import tech.lapsa.esbd.dao.elements.VehicleAgeClassService;
import tech.lapsa.esbd.dao.elements.VehicleAgeClassService.VehicleAgeClassServiceLocal;
import tech.lapsa.esbd.dao.elements.VehicleAgeClassService.VehicleAgeClassServiceRemote;

@Singleton(name = VehicleAgeClassService.BEAN_NAME)
public class VehicleAgeClassServiceBean extends AElementsService<VehicleAgeClass, Integer>
	implements VehicleAgeClassServiceLocal, VehicleAgeClassServiceRemote {

    public VehicleAgeClassServiceBean() {
	super(VehicleAgeClassMapping.getInstance()::forId, VehicleAgeClassService.class);
    }
}
