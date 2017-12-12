package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.KZCityService;
import tech.lapsa.insurance.esbd.elements.KZCityService.KZCityServiceRemote;

@Dependent
public class KZCityServiceProducer {

    @EJB
    private KZCityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public KZCityService getEjb() {
	return ejb;
    }
}
