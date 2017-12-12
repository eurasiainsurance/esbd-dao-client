package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.elements.InsuranceClassTypeService;
import tech.lapsa.insurance.esbd.elements.InsuranceClassTypeService.InsuranceClassTypeServiceRemote;

@Dependent
public class InsuranceClassTypeServiceProducer {

    @EJB
    private InsuranceClassTypeServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public InsuranceClassTypeService getEjb() {
	return ejb;
    }
}
