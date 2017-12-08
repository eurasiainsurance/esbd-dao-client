package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.CancelationReason;

public interface CancelationReasonService extends ElementsService<CancelationReason, Integer> {

    @Local
    public interface CancelationReasonServiceLocal extends CancelationReasonService {
    }

    @Remote
    public interface CancelationReasonServiceRemote extends CancelationReasonService {
    }
}
