package tech.lapsa.insurance.esbd.producer.local;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.SubjectEntityService;
import tech.lapsa.insurance.esbd.entities.SubjectEntityService.SubjectEntityServiceLocal;

@Dependent
public class SubjectEntityServiceProducer {

    @EJB
    private SubjectEntityServiceLocal ejb;

    @Produces
    @EJBViaCDI
    public SubjectEntityService getEjb() {
	return ejb;
    }
}
