package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.CountryService;
import tech.lapsa.insurance.esbd.elements.CountryService.CountryServiceLocal;

@Dependent
public class CountryServiceProducer {

    @EJB
    private CountryServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public CountryService getEjb() {
	return ejb;
    }
}
