package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.IdentityCardType;

public interface IdentityCardTypeService extends ElementsService<IdentityCardType, Integer> {

    @Local
    public interface IdentityCardTypeServiceLocal extends IdentityCardTypeService {
    }

    @Remote
    public interface IdentityCardTypeServiceRemote extends IdentityCardTypeService {
    }
}
