package tech.lapsa.esbd.dao.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.MaritalStatus;

import tech.lapsa.esbd.dao.elements.MaritalStatusService.MaritalStatusServiceLocal;
import tech.lapsa.esbd.dao.elements.MaritalStatusService.MaritalStatusServiceRemote;
import tech.lapsa.esbd.dao.elements.mapping.MaritalStatusMapping;

@Singleton(name = MaritalStatusService.BEAN_NAME)
public class MaritalStatusServiceBean
	extends AElementsService<MaritalStatus>
	implements MaritalStatusServiceLocal, MaritalStatusServiceRemote {

    public MaritalStatusServiceBean() {
	super(MaritalStatusService.class, MaritalStatusMapping.getInstance()::forId);
    }
}
