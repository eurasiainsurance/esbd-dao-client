package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.country.KZArea;

import tech.lapsa.esbd.dao.elements.ElementsService;

public interface KZAreaService extends ElementsService<KZArea> {

    public static final String BEAN_NAME = "KZAreaServiceBean";

    @Local
    public interface KZAreaServiceLocal extends ElementsServiceLocal<KZArea>, KZAreaService {
    }

    @Remote
    public interface KZAreaServiceRemote extends ElementsServiceRemote<KZArea>, KZAreaService {
    }
}
