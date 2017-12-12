package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntityService;
import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceRemote;

@Dependent
public class InsuranceCompanyEntityServiceProducer {

    @EJB
    private InsuranceCompanyEntityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public InsuranceCompanyEntityService getEjb() {
	return ejb;
    }
}
