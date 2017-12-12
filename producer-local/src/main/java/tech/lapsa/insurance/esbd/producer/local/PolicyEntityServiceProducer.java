package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.PolicyEntityService;
import tech.lapsa.insurance.esbd.entities.PolicyEntityService.PolicyEntityServiceLocal;

@Dependent
public class PolicyEntityServiceProducer {

    @EJB
    private PolicyEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public PolicyEntityService getEjb() {
	return ejb;
    }
}
