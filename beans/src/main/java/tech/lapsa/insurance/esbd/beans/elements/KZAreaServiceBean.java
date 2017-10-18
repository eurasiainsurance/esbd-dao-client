package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.insurance.esbd.beans.elements.mapping.KZAreaMapping;
import tech.lapsa.insurance.esbd.elements.KZAreaService;

@Singleton
public class KZAreaServiceBean extends AElementsService<KZArea, Integer> implements KZAreaService {

    public KZAreaServiceBean() {
	super(KZAreaMapping.getInstance()::forId);
    }
}
