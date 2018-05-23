package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.esbd.dao.IElementsService;

public interface CancelationReasonService extends IElementsService<CancelationReason> {

    public static final String BEAN_NAME = "CancelationReasonServiceBean";

    @Local
    public interface CancelationReasonServiceLocal
	    extends IlementsServiceLocal<CancelationReason>, CancelationReasonService {
    }

    @Remote
    public interface CancelationReasonServiceRemote
	    extends IlementsServiceRemote<CancelationReason>, CancelationReasonService {
    }
}
