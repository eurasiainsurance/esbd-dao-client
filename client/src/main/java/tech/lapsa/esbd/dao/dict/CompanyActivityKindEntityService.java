package tech.lapsa.esbd.dao.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface CompanyActivityKindEntityService extends DictionaryEntityService<CompanyActivityKindEntity> {

    public static final String BEAN_NAME = "CompanyActivityKindEntityServiceBean";

    @Local
    public interface CompanyActivityKindEntityServiceLocal
	    extends DictionaryEntityServiceLocal<CompanyActivityKindEntity>, CompanyActivityKindEntityService {
    }

    @Remote
    public interface CompanyActivityKindEntityServiceRemote
	    extends DictionaryEntityServiceRemote<CompanyActivityKindEntity>, CompanyActivityKindEntityService {
    }
}
