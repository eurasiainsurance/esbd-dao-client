package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.VehicleModelEntityService;
import tech.lapsa.insurance.esbd.entities.VehicleModelEntityService.VehicleModelEntityServiceLocal;

@Dependent
public class VehicleModelEntityServiceProducer {

    @EJB
    private VehicleModelEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public VehicleModelEntityService getEjb() {
	return ejb;
    }
}
