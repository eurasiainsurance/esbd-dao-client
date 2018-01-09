package tech.lapsa.esbd.dao.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.esbd.dao.elements.GenderService.GenderServiceLocal;
import tech.lapsa.esbd.dao.elements.GenderService.GenderServiceRemote;
import tech.lapsa.esbd.dao.elements.mapping.SexMapping;

@Singleton(name = GenderService.BEAN_NAME)
public class GenderServiceBean
	extends AElementsService<Sex>
	implements GenderServiceLocal, GenderServiceRemote {

    public GenderServiceBean() {
	super(GenderService.class, SexMapping.getInstance()::forId);
    }
}
