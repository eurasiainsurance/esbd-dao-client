package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.insurance.esbd.beans.elements.mapping.IdentityCardTypeMapping;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService;

@Singleton
public class IdentityCardTypeServiceBean extends AElementsService<IdentityCardType, Integer>
	implements IdentityCardTypeService {

    public IdentityCardTypeServiceBean() {
	super(IdentityCardTypeMapping.getInstance()::forId);
    }
}
