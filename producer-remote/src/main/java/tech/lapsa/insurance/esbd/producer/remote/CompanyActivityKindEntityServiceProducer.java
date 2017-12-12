package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceRemote;

@Dependent
public class CompanyActivityKindEntityServiceProducer {

    @EJB
    private CompanyActivityKindEntityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public CompanyActivityKindEntityService getEjb() {
	return ejb;
    }
}
