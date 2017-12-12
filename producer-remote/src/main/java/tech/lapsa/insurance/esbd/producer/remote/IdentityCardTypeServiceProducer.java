package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService.IdentityCardTypeServiceRemote;

@Dependent
public class IdentityCardTypeServiceProducer {

    @EJB
    private IdentityCardTypeServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public IdentityCardTypeService getEjb() {
	return ejb;
    }
}
