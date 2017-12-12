package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.VehicleEntityService;
import tech.lapsa.insurance.esbd.entities.VehicleEntityService.VehicleEntityServiceLocal;

@Dependent
public class VehicleEntityServiceProducer {

    @EJB
    private VehicleEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public VehicleEntityService getEjb() {
	return ejb;
    }
}
