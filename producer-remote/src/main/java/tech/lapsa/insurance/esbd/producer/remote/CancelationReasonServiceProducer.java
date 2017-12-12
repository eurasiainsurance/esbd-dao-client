package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.CancelationReasonService;
import tech.lapsa.insurance.esbd.elements.CancelationReasonService.CancelationReasonServiceRemote;

@Dependent
public class CancelationReasonServiceProducer {

    @EJB
    private CancelationReasonServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public CancelationReasonService getEjb() {
	return ejb;
    }
}
