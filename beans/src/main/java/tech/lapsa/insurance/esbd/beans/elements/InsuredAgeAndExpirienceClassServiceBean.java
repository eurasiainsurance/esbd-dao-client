package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.InsuredAgeAndExpirienceClassMapping;
import tech.lapsa.insurance.esbd.elements.InsuredAgeAndExpirienceClassService;
import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class InsuredAgeAndExpirienceClassServiceBean implements InsuredAgeAndExpirienceClassService {

    @Override
    public InsuredAgeAndExpirienceClass getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	InsuredAgeAndExpirienceClass result = InsuredAgeAndExpirienceClassMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(
		    InsuredAgeAndExpirienceClass.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
