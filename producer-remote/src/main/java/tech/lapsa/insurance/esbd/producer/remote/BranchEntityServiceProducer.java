package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.dict.BranchEntityService;
import tech.lapsa.insurance.esbd.dict.BranchEntityService.BranchEntityServiceRemote;

@Dependent
public class BranchEntityServiceProducer {

    @EJB
    private BranchEntityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public BranchEntityService getEjb() {
	return ejb;
    }
}
