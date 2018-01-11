package tech.lapsa.esbd.dao.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.esbd.dao.elements.IdentityCardTypeService.IdentityCardTypeServiceLocal;
import tech.lapsa.esbd.dao.elements.IdentityCardTypeService.IdentityCardTypeServiceRemote;
import tech.lapsa.esbd.dao.elements.mapping.IdentityCardTypeMapping;

@Singleton(name = IdentityCardTypeService.BEAN_NAME)
public class IdentityCardTypeServiceBean
	extends AElementsService<IdentityCardType>
	implements IdentityCardTypeServiceLocal, IdentityCardTypeServiceRemote {

    public IdentityCardTypeServiceBean() {
	super(IdentityCardTypeService.class, IdentityCardTypeMapping.getInstance()::forId, IdentityCardType.class);
    }
}
