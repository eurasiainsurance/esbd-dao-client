package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.insurance.esbd.beans.elements.mapping.KZAreaMapping;
import tech.lapsa.insurance.esbd.elements.KZAreaService;
import tech.lapsa.insurance.esbd.elements.KZAreaService.KZAreaServiceLocal;
import tech.lapsa.insurance.esbd.elements.KZAreaService.KZAreaServiceRemote;

@Singleton(name = KZAreaService.BEAN_NAME)
public class KZAreaServiceBean extends AElementsService<KZArea, Integer>
	implements KZAreaServiceLocal, KZAreaServiceRemote {

    public KZAreaServiceBean() {
	super(KZAreaMapping.getInstance()::forId);
    }
}
