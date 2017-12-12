package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.VehicleManufacturerEntityService;
import tech.lapsa.insurance.esbd.entities.VehicleManufacturerEntityService.VehicleManufacturerEntityServiceLocal;

@Dependent
public class VehicleManufacturerEntityServiceProducer {

    @EJB
    private VehicleManufacturerEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public VehicleManufacturerEntityService getEjb() {
	return ejb;
    }
}
