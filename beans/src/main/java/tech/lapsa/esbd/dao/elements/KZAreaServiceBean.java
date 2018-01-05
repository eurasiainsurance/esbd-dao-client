package tech.lapsa.esbd.dao.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.beans.elements.mapping.KZAreaMapping;
import tech.lapsa.esbd.dao.elements.KZAreaService;
import tech.lapsa.esbd.dao.elements.KZAreaService.KZAreaServiceLocal;
import tech.lapsa.esbd.dao.elements.KZAreaService.KZAreaServiceRemote;

@Singleton(name = KZAreaService.BEAN_NAME)
public class KZAreaServiceBean extends AElementsService<KZArea, Integer>
	implements KZAreaServiceLocal, KZAreaServiceRemote {

    public KZAreaServiceBean() {
	super(KZAreaMapping.getInstance()::forId, KZAreaService.class);
    }
}
