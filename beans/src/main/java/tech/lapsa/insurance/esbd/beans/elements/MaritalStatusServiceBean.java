package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.insurance.esbd.beans.elements.mapping.MaritalStatusMapping;
import tech.lapsa.insurance.esbd.elements.MaritalStatusService;

@Singleton
public class MaritalStatusServiceBean extends AElementsService<MaritalStatus, Integer> implements MaritalStatusService {

    public MaritalStatusServiceBean() {
	super(MaritalStatusMapping.getInstance()::forId);
    }
}
