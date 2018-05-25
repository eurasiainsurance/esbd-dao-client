package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.country.KZCity;

public interface KZCityService extends IDictElementsService<KZCity> {

    public static final String BEAN_NAME = "KZCityServiceBean";

    @Local
    public interface KZCityServiceLocal extends IDictElementsServiceLocal<KZCity>, KZCityService {
    }

    @Remote
    public interface KZCityServiceRemote extends IDictElementsServiceRemote<KZCity>, KZCityService {
    }
}
