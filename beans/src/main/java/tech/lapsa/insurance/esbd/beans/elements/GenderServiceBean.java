package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.insurance.esbd.beans.elements.mapping.SexMapping;
import tech.lapsa.insurance.esbd.elements.GenderService.GenderServiceLocal;
import tech.lapsa.insurance.esbd.elements.GenderService.GenderServiceRemote;

@Singleton
public class GenderServiceBean extends AElementsService<Sex, Integer>
	implements GenderServiceLocal, GenderServiceRemote {

    public GenderServiceBean() {
	super(SexMapping.getInstance()::forId);
    }
}
