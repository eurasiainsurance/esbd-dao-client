package tech.lapsa.esbd.dao.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.country.KZCity;

import tech.lapsa.esbd.dao.elements.KZCityService;
import tech.lapsa.esbd.dao.elements.KZCityService.KZCityServiceLocal;
import tech.lapsa.esbd.dao.elements.KZCityService.KZCityServiceRemote;
import tech.lapsa.esbd.dao.elements.mapping.KZCityMapping;

@Singleton(name = KZCityService.BEAN_NAME)
public class KZCityServiceBean extends AElementsService<KZCity, Integer>
	implements KZCityServiceLocal, KZCityServiceRemote {

    public KZCityServiceBean() {
	super(KZCityMapping.getInstance()::forId, KZCityService.class);
    }
}