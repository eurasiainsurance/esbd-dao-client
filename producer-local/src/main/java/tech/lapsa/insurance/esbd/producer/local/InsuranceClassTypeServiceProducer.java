package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.InsuranceClassTypeService;
import tech.lapsa.insurance.esbd.elements.InsuranceClassTypeService.InsuranceClassTypeServiceLocal;

@Dependent
public class InsuranceClassTypeServiceProducer {

    @EJB
    private InsuranceClassTypeServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public InsuranceClassTypeService getEjb() {
	return ejb;
    }
}
