package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.PaymentType;

import tech.lapsa.esbd.dao.elements.ElementsService;

public interface PaymentTypeService extends ElementsService<PaymentType> {

    public static final String BEAN_NAME = "PaymentTypeServiceBean";

    @Local
    public interface PaymentTypeServiceLocal
	    extends ElementsServiceLocal<PaymentType>, PaymentTypeService {
    }

    @Remote
    public interface PaymentTypeServiceRemote
	    extends ElementsServiceRemote<PaymentType>, PaymentTypeService {
    }
}
