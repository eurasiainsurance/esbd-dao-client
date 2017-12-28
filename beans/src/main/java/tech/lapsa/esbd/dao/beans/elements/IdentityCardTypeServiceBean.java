package tech.lapsa.esbd.dao.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.esbd.dao.beans.elements.mapping.IdentityCardTypeMapping;
import tech.lapsa.esbd.dao.elements.IdentityCardTypeService;
import tech.lapsa.esbd.dao.elements.IdentityCardTypeService.IdentityCardTypeServiceLocal;
import tech.lapsa.esbd.dao.elements.IdentityCardTypeService.IdentityCardTypeServiceRemote;

@Singleton(name = IdentityCardTypeService.BEAN_NAME)
public class IdentityCardTypeServiceBean extends AElementsService<IdentityCardType, Integer>
	implements IdentityCardTypeServiceLocal, IdentityCardTypeServiceRemote {

    public IdentityCardTypeServiceBean() {
	super(IdentityCardTypeMapping.getInstance()::forId, IdentityCardTypeService.class);
    }
}
