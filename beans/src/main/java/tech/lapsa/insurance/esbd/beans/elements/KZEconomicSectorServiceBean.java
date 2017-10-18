package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.insurance.esbd.beans.elements.mapping.KZEconomicSectorMapping;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService;

@Singleton
public class KZEconomicSectorServiceBean extends AElementsService<KZEconomicSector, Integer> implements KZEconomicSectorService {

    public KZEconomicSectorServiceBean() {
	super(KZEconomicSectorMapping.getInstance()::forId);
    }
}
