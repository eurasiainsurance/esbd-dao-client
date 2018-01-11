package tech.lapsa.esbd.dao.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.elements.KZAreaService.KZAreaServiceLocal;
import tech.lapsa.esbd.dao.elements.KZAreaService.KZAreaServiceRemote;
import tech.lapsa.esbd.dao.elements.mapping.KZAreaMapping;

@Singleton(name = KZAreaService.BEAN_NAME)
public class KZAreaServiceBean
	extends AElementsService<KZArea>
	implements KZAreaServiceLocal, KZAreaServiceRemote {

    public KZAreaServiceBean() {
	super(KZAreaService.class, KZAreaMapping.getInstance()::forId, KZArea.class);
    }
}
