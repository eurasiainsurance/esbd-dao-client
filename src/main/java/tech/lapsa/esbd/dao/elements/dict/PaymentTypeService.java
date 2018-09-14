package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.PaymentType;

public interface PaymentTypeService extends IDictElementsService<PaymentType> {

    public static final String BEAN_NAME = "PaymentTypeServiceBean";

    @Local
    public interface PaymentTypeServiceLocal
	    extends IDictElementsServiceLocal<PaymentType>, PaymentTypeService {
    }

    @Remote
    public interface PaymentTypeServiceRemote
	    extends IDictElementsServiceRemote<PaymentType>, PaymentTypeService {
    }
}
