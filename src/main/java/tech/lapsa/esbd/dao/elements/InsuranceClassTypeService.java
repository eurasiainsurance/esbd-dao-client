package tech.lapsa.esbd.dao.elements.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.esbd.dao.IElementsService;

public interface InsuranceClassTypeService extends IElementsService<InsuranceClassType> {

    public static final String BEAN_NAME = "InsuranceClassTypeServiceBean";

    @Local
    public interface InsuranceClassTypeServiceLocal
	    extends IlementsServiceLocal<InsuranceClassType>, InsuranceClassTypeService {
    }

    @Remote
    public interface InsuranceClassTypeServiceRemote
	    extends IlementsServiceRemote<InsuranceClassType>, InsuranceClassTypeService {
    }
}
