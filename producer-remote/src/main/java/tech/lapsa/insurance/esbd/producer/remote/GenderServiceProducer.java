package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.GenderService;
import tech.lapsa.insurance.esbd.elements.GenderService.GenderServiceRemote;

@Dependent
public class GenderServiceProducer {

    @EJB
    private GenderServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public GenderService getEjb() {
	return ejb;
    }
}
