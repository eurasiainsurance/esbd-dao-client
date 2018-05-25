package tech.lapsa.esbd.dao.elements;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuranceClassType;

public interface InsuranceClassTypeService extends IElementsService<InsuranceClassType> {

    public static final String BEAN_NAME = "InsuranceClassTypeServiceBean";

    @Local
    public interface InsuranceClassTypeServiceLocal
	    extends IElementsServiceLocal<InsuranceClassType>, InsuranceClassTypeService {
    }

    @Remote
    public interface InsuranceClassTypeServiceRemote
	    extends IElementsServiceRemote<InsuranceClassType>, InsuranceClassTypeService {
    }
}
