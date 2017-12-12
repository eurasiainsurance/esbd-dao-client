package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceLocal;

@Dependent
public class SubjectCompanyEntityServiceProducer {

    @EJB
    private SubjectCompanyEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public SubjectCompanyEntityService getEjb() {
	return ejb;
    }
}
