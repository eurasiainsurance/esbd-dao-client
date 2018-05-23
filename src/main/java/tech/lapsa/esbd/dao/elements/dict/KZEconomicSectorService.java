package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.economic.KZEconomicSector;

import tech.lapsa.esbd.dao.IElementsService;

public interface KZEconomicSectorService extends IElementsService<KZEconomicSector> {

    public static final String BEAN_NAME = "KZEconomicSectorServiceBean";

    @Local
    public interface KZEconomicSectorServiceLocal
	    extends IlementsServiceLocal<KZEconomicSector>, KZEconomicSectorService {
    }

    @Remote
    public interface KZEconomicSectorServiceRemote
	    extends IlementsServiceRemote<KZEconomicSector>, KZEconomicSectorService {
    }
}
