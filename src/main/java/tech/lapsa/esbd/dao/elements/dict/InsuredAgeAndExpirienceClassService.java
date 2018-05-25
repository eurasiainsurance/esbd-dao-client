package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

public interface InsuredAgeAndExpirienceClassService
	extends IDictElementsService<InsuredAgeAndExpirienceClass> {

    public static final String BEAN_NAME = "InsuredAgeAndExpirienceClassServiceBean";

    @Local
    public interface InsuredAgeAndExpirienceClassServiceLocal
	    extends IDictElementsServiceLocal<InsuredAgeAndExpirienceClass>, InsuredAgeAndExpirienceClassService {
    }

    @Remote
    public interface InsuredAgeAndExpirienceClassServiceRemote
	    extends IDictElementsServiceRemote<InsuredAgeAndExpirienceClass>, InsuredAgeAndExpirienceClassService {
    }
}
