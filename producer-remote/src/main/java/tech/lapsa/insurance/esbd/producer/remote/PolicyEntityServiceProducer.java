package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.PolicyEntityService;
import tech.lapsa.insurance.esbd.entities.PolicyEntityService.PolicyEntityServiceRemote;

@Dependent
public class PolicyEntityServiceProducer {

    @EJB
    private PolicyEntityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public PolicyEntityService getEjb() {
	return ejb;
    }
}
