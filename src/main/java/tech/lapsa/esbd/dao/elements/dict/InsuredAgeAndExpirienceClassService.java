package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

import tech.lapsa.esbd.dao.IElementsService;

public interface InsuredAgeAndExpirienceClassService
	extends IElementsService<InsuredAgeAndExpirienceClass> {

    public static final String BEAN_NAME = "InsuredAgeAndExpirienceClassServiceBean";

    @Local
    public interface InsuredAgeAndExpirienceClassServiceLocal
	    extends IlementsServiceLocal<InsuredAgeAndExpirienceClass>, InsuredAgeAndExpirienceClassService {
    }

    @Remote
    public interface InsuredAgeAndExpirienceClassServiceRemote
	    extends IlementsServiceRemote<InsuredAgeAndExpirienceClass>, InsuredAgeAndExpirienceClassService {
    }
}
