package tech.lapsa.esbd.dao.beans.elements;

import javax.ejb.Singleton;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.beans.elements.mapping.InsuredAgeAndExpirienceClassMapping;
import tech.lapsa.esbd.dao.elements.InsuredAgeAndExpirienceClassService;
import tech.lapsa.esbd.dao.elements.InsuredAgeAndExpirienceClassService.InsuredAgeAndExpirienceClassServiceLocal;
import tech.lapsa.esbd.dao.elements.InsuredAgeAndExpirienceClassService.InsuredAgeAndExpirienceClassServiceRemote;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;

@Singleton(name = InsuredAgeAndExpirienceClassService.BEAN_NAME)
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
