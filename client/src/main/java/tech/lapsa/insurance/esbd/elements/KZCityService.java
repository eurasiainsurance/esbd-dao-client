package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.country.KZCity;

public interface KZCityService extends ElementsService<KZCity, Integer> {

    public static final String BEAN_NAME = "KZCityServiceBean";

    @Local
    public interface KZCityServiceLocal extends KZCityService {
    }

    @Remote
    public interface KZCityServiceRemote extends KZCityService {
    }
}
