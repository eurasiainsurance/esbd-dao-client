package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.country.KZCity;

import tech.lapsa.insurance.esbd.beans.elements.mapping.KZCityMapping;
import tech.lapsa.insurance.esbd.elements.KZCityService;

@Singleton
public class KZCityServiceBean extends AElementsService<KZCity, Integer> implements KZCityService {

    public KZCityServiceBean() {
	super(KZCityMapping.getInstance()::forId);
    }
}