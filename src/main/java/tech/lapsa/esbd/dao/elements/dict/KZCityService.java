package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.country.KZCity;

import tech.lapsa.esbd.dao.IElementsService;

public interface KZCityService extends IElementsService<KZCity> {

    public static final String BEAN_NAME = "KZCityServiceBean";

    @Local
    public interface KZCityServiceLocal extends IlementsServiceLocal<KZCity>, KZCityService {
    }

    @Remote
    public interface KZCityServiceRemote extends IlementsServiceRemote<KZCity>, KZCityService {
    }
}
