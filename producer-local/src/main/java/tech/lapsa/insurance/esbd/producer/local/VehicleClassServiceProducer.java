package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.VehicleClassService;
import tech.lapsa.insurance.esbd.elements.VehicleClassService.VehicleClassServiceLocal;

@Dependent
public class VehicleClassServiceProducer {

    @EJB
    private VehicleClassServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public VehicleClassService getEjb() {
	return ejb;
    }
}
