package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceLocal;

@Dependent
public class CompanyActivityKindEntityServiceProducer {

    @EJB
    private CompanyActivityKindEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public CompanyActivityKindEntityService getEjb() {
	return ejb;
    }
}
