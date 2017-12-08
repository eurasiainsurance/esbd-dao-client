package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.VehicleAgeClass;

import tech.lapsa.insurance.esbd.beans.elements.mapping.VehicleAgeClassMapping;
import tech.lapsa.insurance.esbd.elements.VehicleAgeClassService.VehicleAgeClassServiceLocal;
import tech.lapsa.insurance.esbd.elements.VehicleAgeClassService.VehicleAgeClassServiceRemote;

@Singleton
public class VehicleAgeClassServiceBean extends AElementsService<VehicleAgeClass, Integer>
	implements VehicleAgeClassServiceLocal, VehicleAgeClassServiceRemote {

    public VehicleAgeClassServiceBean() {
	super(VehicleAgeClassMapping.getInstance()::forId);
    }
}
