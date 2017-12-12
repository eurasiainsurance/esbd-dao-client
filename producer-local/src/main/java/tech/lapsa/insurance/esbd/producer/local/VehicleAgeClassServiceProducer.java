package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.VehicleAgeClassService;
import tech.lapsa.insurance.esbd.elements.VehicleAgeClassService.VehicleAgeClassServiceLocal;

@Dependent
public class VehicleAgeClassServiceProducer {

    @EJB
    private VehicleAgeClassServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public VehicleAgeClassService getEjb() {
	return ejb;
    }
}
