package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.insurance.esbd.beans.elements.mapping.MaritalStatusMapping;
import tech.lapsa.insurance.esbd.elements.MaritalStatusService;
import tech.lapsa.insurance.esbd.elements.MaritalStatusService.MaritalStatusServiceLocal;
import tech.lapsa.insurance.esbd.elements.MaritalStatusService.MaritalStatusServiceRemote;

@Singleton(name = MaritalStatusService.BEAN_NAME)
public class MaritalStatusServiceBean extends AElementsService<MaritalStatus, Integer>
	implements MaritalStatusServiceLocal, MaritalStatusServiceRemote {

    public MaritalStatusServiceBean() {
	super(MaritalStatusMapping.getInstance()::forId);
    }
}
