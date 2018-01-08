package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.kz.country.KZArea;

public interface KZAreaService extends ElementsService<KZArea, Integer> {

    public static final String BEAN_NAME = "KZAreaServiceBean";

    @Local
    public interface KZAreaServiceLocal extends ElementsServiceLocal<KZArea, Integer>, KZAreaService {
    }

    @Remote
    public interface KZAreaServiceRemote extends ElementsServiceRemote<KZArea, Integer>, KZAreaService {
    }
}
