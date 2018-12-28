package tech.lapsa.esbd.dao.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.domain.entities.SubjectEntity;

public interface SubjectEntityService extends GeneralSubjectEntityService<SubjectEntity> {

    public static final String BEAN_NAME = "SubjectEntityServiceBean";

    @Local
    public interface SubjectEntityServiceLocal
	    extends GeneralSubjectEntityServiceLocal<SubjectEntity>, SubjectEntityService {
    }

    @Remote
    public interface SubjectEntityServiceRemote
	    extends GeneralSubjectEntityServiceRemote<SubjectEntity>, SubjectEntityService {
    }
}
