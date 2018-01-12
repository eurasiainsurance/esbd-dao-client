package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

public interface InsuredAgeAndExpirienceClassService
	extends ElementsService<InsuredAgeAndExpirienceClass> {

    public static final String BEAN_NAME = "InsuredAgeAndExpirienceClassServiceBean";

    @Local
    public interface InsuredAgeAndExpirienceClassServiceLocal
	    extends ElementsServiceLocal<InsuredAgeAndExpirienceClass>, InsuredAgeAndExpirienceClassService {
    }

    @Remote
    public interface InsuredAgeAndExpirienceClassServiceRemote
	    extends ElementsServiceRemote<InsuredAgeAndExpirienceClass>, InsuredAgeAndExpirienceClassService {
    }
}
