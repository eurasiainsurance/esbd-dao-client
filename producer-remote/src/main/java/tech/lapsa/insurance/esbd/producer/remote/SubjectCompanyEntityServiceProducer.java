package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceRemote;

@Dependent
public class SubjectCompanyEntityServiceProducer {

    @EJB
    private SubjectCompanyEntityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public SubjectCompanyEntityService getEjb() {
	return ejb;
    }
}
