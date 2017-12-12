package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.VehicleAgeClassService;
import tech.lapsa.insurance.esbd.elements.VehicleAgeClassService.VehicleAgeClassServiceRemote;

@Dependent
public class VehicleAgeClassServiceProducer {

    @EJB
    private VehicleAgeClassServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public VehicleAgeClassService getEjb() {
	return ejb;
    }
}
