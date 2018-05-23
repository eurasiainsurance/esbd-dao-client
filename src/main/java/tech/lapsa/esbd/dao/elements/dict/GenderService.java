package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.esbd.dao.IElementsService;

public interface GenderService extends IElementsService<Sex> {

    public static final String BEAN_NAME = "GenderServiceBean";

    @Local
    public interface GenderServiceLocal extends IlementsServiceLocal<Sex>, GenderService {
    }

    @Remote
    public interface GenderServiceRemote extends IlementsServiceRemote<Sex>, GenderService {
    }
}
