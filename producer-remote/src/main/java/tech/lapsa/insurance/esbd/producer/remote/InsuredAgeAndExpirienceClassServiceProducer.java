package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.InsuredAgeAndExpirienceClassService;
import tech.lapsa.insurance.esbd.elements.InsuredAgeAndExpirienceClassService.InsuredAgeAndExpirienceClassServiceRemote;

@Dependent
public class InsuredAgeAndExpirienceClassServiceProducer {

    @EJB
    private InsuredAgeAndExpirienceClassServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public InsuredAgeAndExpirienceClassService getEjb() {
	return ejb;
    }
}
