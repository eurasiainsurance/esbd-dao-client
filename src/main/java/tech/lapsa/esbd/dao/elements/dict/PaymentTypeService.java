package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.PaymentType;

import tech.lapsa.esbd.dao.IElementsService;

public interface PaymentTypeService extends IElementsService<PaymentType> {

    public static final String BEAN_NAME = "PaymentTypeServiceBean";

    @Local
    public interface PaymentTypeServiceLocal
	    extends IlementsServiceLocal<PaymentType>, PaymentTypeService {
    }

    @Remote
    public interface PaymentTypeServiceRemote
	    extends IlementsServiceRemote<PaymentType>, PaymentTypeService {
    }
}
