package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.CancelationReason;

public interface CancelationReasonService extends IDictElementsService<CancelationReason> {

    public static final String BEAN_NAME = "CancelationReasonServiceBean";

    @Local
    public interface CancelationReasonServiceLocal
	    extends IDictElementsServiceLocal<CancelationReason>, CancelationReasonService {
    }

    @Remote
    public interface CancelationReasonServiceRemote
	    extends IDictElementsServiceRemote<CancelationReason>, CancelationReasonService {
    }
}
