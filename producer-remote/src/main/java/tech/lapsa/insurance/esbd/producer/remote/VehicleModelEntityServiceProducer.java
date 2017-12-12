package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.VehicleModelEntityService;
import tech.lapsa.insurance.esbd.entities.VehicleModelEntityService.VehicleModelEntityServiceRemote;

@Dependent
public class VehicleModelEntityServiceProducer {

    @EJB
    private VehicleModelEntityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public VehicleModelEntityService getEjb() {
	return ejb;
    }
}
