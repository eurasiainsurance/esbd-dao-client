package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService.KZEconomicSectorServiceLocal;

@Dependent
public class KZEconomicSectorServiceProducer {

    @EJB
    private KZEconomicSectorServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public KZEconomicSectorService getEjb() {
	return ejb;
    }
}
