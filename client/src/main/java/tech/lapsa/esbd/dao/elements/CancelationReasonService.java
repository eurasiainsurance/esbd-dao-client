package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.CancelationReason;

public interface CancelationReasonService extends ElementsService<CancelationReason, Integer> {

    public static final String BEAN_NAME = "CancelationReasonServiceBean";

    @Local
    public interface CancelationReasonServiceLocal extends CancelationReasonService {
    }

    @Remote
    public interface CancelationReasonServiceRemote extends CancelationReasonService {
    }
}
