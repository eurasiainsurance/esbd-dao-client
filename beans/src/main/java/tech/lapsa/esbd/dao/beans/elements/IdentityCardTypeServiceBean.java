package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.insurance.esbd.beans.elements.mapping.IdentityCardTypeMapping;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService.IdentityCardTypeServiceLocal;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService.IdentityCardTypeServiceRemote;

@Singleton(name = IdentityCardTypeService.BEAN_NAME)
public class IdentityCardTypeServiceBean extends AElementsService<IdentityCardType, Integer>
	implements IdentityCardTypeServiceLocal, IdentityCardTypeServiceRemote {

    public IdentityCardTypeServiceBean() {
	super(IdentityCardTypeMapping.getInstance()::forId);
    }
}
