package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.insurance.esbd.beans.elements.mapping.CancelationReasonMapping;
import tech.lapsa.insurance.esbd.elements.CancelationReasonServiceService;

@Singleton
public class CancelationReasonServiceBean extends AElementsService<CancelationReason, Integer>
	implements CancelationReasonService {

    public CancelationReasonServiceBean() {
	super(CancelationReasonMapping.getInstance()::forId);
    }
}
