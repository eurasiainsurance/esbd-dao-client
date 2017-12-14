package tech.lapsa.insurance.esbd.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.InsuredAgeAndExpirienceClassMapping;
import tech.lapsa.insurance.esbd.elements.InsuredAgeAndExpirienceClassService.InsuredAgeAndExpirienceClassServiceLocal;
import tech.lapsa.insurance.esbd.elements.InsuredAgeAndExpirienceClassService.InsuredAgeAndExpirienceClassServiceRemote;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;

@Singleton
public class InsuredAgeAndExpirienceClassServiceBean
	implements InsuredAgeAndExpirienceClassServiceLocal, InsuredAgeAndExpirienceClassServiceRemote {

    @Override
    public InsuredAgeAndExpirienceClass getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	final InsuredAgeAndExpirienceClass result = InsuredAgeAndExpirienceClassMapping.getInstance().forId(id);
	if (result == null)
	    throw new NotFound(
		    InsuredAgeAndExpirienceClass.class.getSimpleName() + " not found with ID = '" + id + "'");
	return result;
    }
}
