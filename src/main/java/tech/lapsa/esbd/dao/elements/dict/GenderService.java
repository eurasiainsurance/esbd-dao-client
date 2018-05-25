package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.Sex;

public interface GenderService extends IDictElementsService<Sex> {

    public static final String BEAN_NAME = "GenderServiceBean";

    @Local
    public interface GenderServiceLocal extends IDictElementsServiceLocal<Sex>, GenderService {
    }

    @Remote
    public interface GenderServiceRemote extends IDictElementsServiceRemote<Sex>, GenderService {
    }
}
