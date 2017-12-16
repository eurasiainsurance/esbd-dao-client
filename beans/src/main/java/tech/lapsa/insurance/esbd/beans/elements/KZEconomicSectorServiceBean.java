package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.insurance.esbd.beans.elements.mapping.KZEconomicSectorMapping;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService.KZEconomicSectorServiceLocal;
import tech.lapsa.insurance.esbd.elements.KZEconomicSectorService.KZEconomicSectorServiceRemote;

@Singleton(name = KZEconomicSectorService.BEAN_NAME)
public class KZEconomicSectorServiceBean extends AElementsService<KZEconomicSector, Integer>
	implements KZEconomicSectorServiceLocal, KZEconomicSectorServiceRemote {

    public KZEconomicSectorServiceBean() {
	super(KZEconomicSectorMapping.getInstance()::forId);
    }
}
