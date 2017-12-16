package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

public interface InsuredAgeAndExpirienceClassService
	extends ElementsService<InsuredAgeAndExpirienceClass, Integer> {

    public static final String BEAN_NAME = "InsuredAgeAndExpirienceClassServiceBean";

    @Local
    public interface InsuredAgeAndExpirienceClassServiceLocal extends InsuredAgeAndExpirienceClassService {
    }

    @Remote
    public interface InsuredAgeAndExpirienceClassServiceRemote extends InsuredAgeAndExpirienceClassService {
    }
}
