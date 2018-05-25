package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.economic.KZEconomicSector;

public interface KZEconomicSectorService extends IDictElementsService<KZEconomicSector> {

    public static final String BEAN_NAME = "KZEconomicSectorServiceBean";

    @Local
    public interface KZEconomicSectorServiceLocal
	    extends IDictElementsServiceLocal<KZEconomicSector>, KZEconomicSectorService {
    }

    @Remote
    public interface KZEconomicSectorServiceRemote
	    extends IDictElementsServiceRemote<KZEconomicSector>, KZEconomicSectorService {
    }
}
