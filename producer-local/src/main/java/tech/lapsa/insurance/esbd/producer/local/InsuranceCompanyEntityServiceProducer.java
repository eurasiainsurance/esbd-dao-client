package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntityService;
import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntityService.InsuranceCompanyEntityServiceLocal;

@Dependent
public class InsuranceCompanyEntityServiceProducer {

    @EJB
    private InsuranceCompanyEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public InsuranceCompanyEntityService getEjb() {
	return ejb;
    }
}
