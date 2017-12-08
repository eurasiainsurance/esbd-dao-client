package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.Sex;

public interface GenderService extends ElementsService<Sex, Integer> {

    @Local
    public interface GenderServiceLocal extends GenderService {
    }

    @Remote
    public interface GenderServiceRemote extends GenderService {
    }
}
