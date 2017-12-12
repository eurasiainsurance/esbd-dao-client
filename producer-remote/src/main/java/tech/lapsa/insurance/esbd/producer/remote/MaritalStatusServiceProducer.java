package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.MaritalStatusService;
import tech.lapsa.insurance.esbd.elements.MaritalStatusService.MaritalStatusServiceRemote;

@Dependent
public class MaritalStatusServiceProducer {

    @EJB
    private MaritalStatusServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public MaritalStatusService getEjb() {
	return ejb;
    }
}
