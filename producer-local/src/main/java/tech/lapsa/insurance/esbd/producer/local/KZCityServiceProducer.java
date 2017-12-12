package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.KZCityService;
import tech.lapsa.insurance.esbd.elements.KZCityService.KZCityServiceLocal;

@Dependent
public class KZCityServiceProducer {

    @EJB
    private KZCityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public KZCityService getEjb() {
	return ejb;
    }
}
