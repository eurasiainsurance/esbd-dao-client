package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.VehicleManufacturerEntityService;
import tech.lapsa.insurance.esbd.entities.VehicleManufacturerEntityService.VehicleManufacturerEntityServiceRemote;

@Dependent
public class VehicleManufacturerEntityServiceProducer {

    @EJB
    private VehicleManufacturerEntityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public VehicleManufacturerEntityService getEjb() {
	return ejb;
    }
}
