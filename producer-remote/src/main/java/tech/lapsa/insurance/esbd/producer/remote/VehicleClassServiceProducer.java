package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.VehicleClassService;
import tech.lapsa.insurance.esbd.elements.VehicleClassService.VehicleClassServiceRemote;

@Dependent
public class VehicleClassServiceProducer {

    @EJB
    private VehicleClassServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public VehicleClassService getEjb() {
	return ejb;
    }
}
