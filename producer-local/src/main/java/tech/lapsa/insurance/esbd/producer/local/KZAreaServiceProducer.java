package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.KZAreaService;
import tech.lapsa.insurance.esbd.elements.KZAreaService.KZAreaServiceLocal;

@Dependent
public class KZAreaServiceProducer {

    @EJB
    private KZAreaServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public KZAreaService getEjb() {
	return ejb;
    }
}
