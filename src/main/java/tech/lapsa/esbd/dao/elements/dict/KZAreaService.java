package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.IElementsService;

public interface KZAreaService extends IElementsService<KZArea> {

    public static final String BEAN_NAME = "KZAreaServiceBean";

    @Local
    public interface KZAreaServiceLocal extends IlementsServiceLocal<KZArea>, KZAreaService {
    }

    @Remote
    public interface KZAreaServiceRemote extends IlementsServiceRemote<KZArea>, KZAreaService {
    }
}
