package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService.IdentityCardTypeServiceLocal;

@Dependent
public class IdentityCardTypeServiceProducer {

    @EJB
    private IdentityCardTypeServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public IdentityCardTypeService getEjb() {
	return ejb;
    }
}
