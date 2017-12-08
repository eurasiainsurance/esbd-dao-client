package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.economic.KZEconomicSector;

public interface KZEconomicSectorService extends ElementsService<KZEconomicSector, Integer> {

    @Local
    public interface KZEconomicSectorServiceLocal extends KZEconomicSectorService {
    }

    @Remote
    public interface KZEconomicSectorServiceRemote extends KZEconomicSectorService {
    }
}
