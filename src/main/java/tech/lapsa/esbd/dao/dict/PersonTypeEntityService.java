package tech.lapsa.esbd.dao.dict;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface PersonTypeEntityService extends DictionaryEntityService<PersonTypeEntity> {

    public static final String BEAN_NAME = "PersonTypeEntityServiceBean";

    @Local
    public interface PersonTypeEntityServiceLocal
	    extends DictionaryEntityServiceLocal<PersonTypeEntity>, PersonTypeEntityService {
    }

    @Remote
    public interface PersonTypeEntityServiceRemote
	    extends DictionaryEntityServiceRemote<PersonTypeEntity>, PersonTypeEntityService {
    }
}
