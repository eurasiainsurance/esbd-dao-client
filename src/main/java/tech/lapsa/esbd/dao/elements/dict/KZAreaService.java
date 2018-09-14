package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.country.KZArea;

public interface KZAreaService extends IDictElementsService<KZArea> {

    public static final String BEAN_NAME = "KZAreaServiceBean";

    @Local
    public interface KZAreaServiceLocal extends IDictElementsServiceLocal<KZArea>, KZAreaService {
    }

    @Remote
    public interface KZAreaServiceRemote extends IDictElementsServiceRemote<KZArea>, KZAreaService {
    }
}
