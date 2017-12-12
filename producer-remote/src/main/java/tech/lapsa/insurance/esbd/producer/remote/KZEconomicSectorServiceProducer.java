package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService.KZEconomicSectorServiceRemote;

@Dependent
public class KZEconomicSectorServiceProducer {

    @EJB
    private KZEconomicSectorServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public KZEconomicSectorService getEjb() {
	return ejb;
    }
}
