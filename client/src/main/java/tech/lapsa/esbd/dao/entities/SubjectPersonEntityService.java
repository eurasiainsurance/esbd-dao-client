package tech.lapsa.esbd.dao.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface SubjectPersonEntityService extends GeneralSubjectEntityService<SubjectPersonEntity> {

    public static final String BEAN_NAME = "SubjectPersonEntityServiceBean";

    @Local
    public interface SubjectPersonEntityServiceLocal
	    extends GeneralSubjectEntityServiceLocal<SubjectPersonEntity>, SubjectPersonEntityService {
    }

    @Remote
    public interface SubjectPersonEntityServiceRemote
	    extends GeneralSubjectEntityServiceRemote<SubjectPersonEntity>, SubjectPersonEntityService {
    }
}
