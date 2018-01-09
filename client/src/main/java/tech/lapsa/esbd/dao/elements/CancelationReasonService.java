package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.CancelationReason;

public interface CancelationReasonService extends ElementsService<CancelationReason> {

    public static final String BEAN_NAME = "CancelationReasonServiceBean";

    @Local
    public interface CancelationReasonServiceLocal
	    extends ElementsServiceLocal<CancelationReason>, CancelationReasonService {
    }

    @Remote
    public interface CancelationReasonServiceRemote
	    extends ElementsServiceRemote<CancelationReason>, CancelationReasonService {
    }
}
