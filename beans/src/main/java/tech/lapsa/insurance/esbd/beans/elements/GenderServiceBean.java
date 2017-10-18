package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.insurance.esbd.beans.elements.mapping.SexMapping;
import tech.lapsa.insurance.esbd.elements.GenderService;

@Singleton
public class GenderServiceBean extends AElementsService<Sex, Integer> implements GenderService {

    public GenderServiceBean() {
	super(SexMapping.getInstance()::forId);
    }
}
