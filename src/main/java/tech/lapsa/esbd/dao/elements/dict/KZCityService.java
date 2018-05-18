package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.country.KZCity;

import tech.lapsa.esbd.dao.elements.ElementsService;

public interface KZCityService extends ElementsService<KZCity> {

    public static final String BEAN_NAME = "KZCityServiceBean";

    @Local
    public interface KZCityServiceLocal extends ElementsServiceLocal<KZCity>, KZCityService {
    }

    @Remote
    public interface KZCityServiceRemote extends ElementsServiceRemote<KZCity>, KZCityService {
    }
}
