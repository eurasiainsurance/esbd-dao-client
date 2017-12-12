package tech.lapsa.insurance.esbd.producer.remote;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import tech.lapsa.insurance.esbd.EJBViaCDI;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntityService;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntityService.SubjectPersonEntityServiceRemote;

@Dependent
public class SubjectPersonEntityServiceProducer {

    @EJB
    private SubjectPersonEntityServiceRemote ejb;

    @Produces
    @EJBViaCDI
    public SubjectPersonEntityService getEjb() {
	return ejb;
    }
}
