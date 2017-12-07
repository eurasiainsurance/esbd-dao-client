package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;

import com.lapsa.insurance.elements.CancelationReason;

@Local
public interface CancelationReasonService extends ElementsService<CancelationReason, Integer> {
}
