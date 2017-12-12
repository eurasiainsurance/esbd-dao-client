package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.CancelationReasonService;
import tech.lapsa.insurance.esbd.elements.CancelationReasonService.CancelationReasonServiceLocal;

@Dependent
public class CancelationReasonServiceProducer {

    @EJB
    private CancelationReasonServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public CancelationReasonService getEjb() {
	return ejb;
    }
}
