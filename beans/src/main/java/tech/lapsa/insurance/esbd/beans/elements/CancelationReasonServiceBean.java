package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.insurance.esbd.beans.elements.mapping.CancelationReasonMapping;
import tech.lapsa.insurance.esbd.elements.CancelationReasonService;
import tech.lapsa.insurance.esbd.elements.CancelationReasonService.CancelationReasonServiceLocal;
import tech.lapsa.insurance.esbd.elements.CancelationReasonService.CancelationReasonServiceRemote;

@Singleton(name = CancelationReasonService.BEAN_NAME)
public class CancelationReasonServiceBean extends AElementsService<CancelationReason, Integer>
	implements CancelationReasonServiceLocal, CancelationReasonServiceRemote {

    public CancelationReasonServiceBean() {
	super(CancelationReasonMapping.getInstance()::forId);
    }
}
