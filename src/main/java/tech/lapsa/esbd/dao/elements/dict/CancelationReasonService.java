package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.esbd.dao.elements.ElementsService;

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
