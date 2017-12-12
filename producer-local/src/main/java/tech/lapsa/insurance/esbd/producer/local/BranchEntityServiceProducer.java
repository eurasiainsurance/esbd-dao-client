package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.dict.BranchEntityService;
import tech.lapsa.insurance.esbd.dict.BranchEntityService.BranchEntityServiceLocal;

@Dependent
public class BranchEntityServiceProducer {

    @EJB
    private BranchEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public BranchEntityService getEjb() {
	return ejb;
    }
}
