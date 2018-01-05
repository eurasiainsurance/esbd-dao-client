package tech.lapsa.esbd.dao.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.beans.elements.mapping.KZEconomicSectorMapping;
import tech.lapsa.esbd.dao.elements.KZEconomicSectorService;
import tech.lapsa.esbd.dao.elements.KZEconomicSectorService.KZEconomicSectorServiceLocal;
import tech.lapsa.esbd.dao.elements.KZEconomicSectorService.KZEconomicSectorServiceRemote;

@Singleton(name = KZEconomicSectorService.BEAN_NAME)
public class KZEconomicSectorServiceBean extends AElementsService<KZEconomicSector, Integer>
	implements KZEconomicSectorServiceLocal, KZEconomicSectorServiceRemote {

    public KZEconomicSectorServiceBean() {
	super(KZEconomicSectorMapping.getInstance()::forId, KZEconomicSectorService.class);
    }
}
