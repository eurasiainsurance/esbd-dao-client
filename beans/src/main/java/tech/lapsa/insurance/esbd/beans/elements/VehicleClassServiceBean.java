package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.insurance.esbd.beans.elements.mapping.VehicleClassMapping;
import tech.lapsa.insurance.esbd.elements.VehicleClassService;

@Singleton
public class VehicleClassServiceBean extends AElementsService<VehicleClass, Integer> implements VehicleClassService {

    public VehicleClassServiceBean() {
	super(VehicleClassMapping.getInstance()::forId);
    }
}
