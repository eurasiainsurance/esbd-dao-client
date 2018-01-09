package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.Sex;

public interface GenderService extends ElementsService<Sex> {

    public static final String BEAN_NAME = "GenderServiceBean";

    @Local
    public interface GenderServiceLocal extends ElementsServiceLocal<Sex>, GenderService {
    }

    @Remote
    public interface GenderServiceRemote extends ElementsServiceRemote<Sex>, GenderService {
    }
}
