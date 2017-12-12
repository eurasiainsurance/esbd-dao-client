package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.KZAreaService;
import tech.lapsa.insurance.esbd.elements.KZAreaService.KZAreaServiceRemote;

@Dependent
public class KZAreaServiceProducer {

    @EJB
    private KZAreaServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public KZAreaService getEjb() {
	return ejb;
    }
}
