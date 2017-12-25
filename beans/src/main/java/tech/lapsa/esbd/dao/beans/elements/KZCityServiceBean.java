package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.esbd.beans.elements.mapping.KZCityMapping;
import tech.lapsa.insurance.esbd.elements.KZCityService;
import tech.lapsa.insurance.esbd.elements.KZCityService.KZCityServiceLocal;
import tech.lapsa.insurance.esbd.elements.KZCityService.KZCityServiceRemote;

@Singleton(name = KZCityService.BEAN_NAME)
public class KZCityServiceBean extends AElementsService<KZCity, Integer>
	implements KZCityServiceLocal, KZCityServiceRemote {

    public KZCityServiceBean() {
	super(KZCityMapping.getInstance()::forId);
    }
}