package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.Sex;

public interface GenderService extends ElementsService<Sex, Integer> {

    public static final String BEAN_NAME = "GenderServiceBean";

    @Local
    public interface GenderServiceLocal extends ElementsServiceLocal<Sex, Integer>, GenderService {
    }

    @Remote
    public interface GenderServiceRemote extends ElementsServiceRemote<Sex, Integer>, GenderService {
    }
}
