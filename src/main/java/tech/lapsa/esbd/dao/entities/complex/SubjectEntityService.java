package tech.lapsa.esbd.dao.entities.complex;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.domain.complex.SubjectEntity;

public interface SubjectEntityService extends ISubjectEntitiesService<SubjectEntity> {

    public static final String BEAN_NAME = "SubjectEntityServiceBean";

    @Local
    public interface SubjectEntityServiceLocal
	    extends ISubjectEntityServiceLocal<SubjectEntity>, SubjectEntityService {
    }

    @Remote
    public interface SubjectEntityServiceRemote
	    extends ISubjectEntityServiceRemote<SubjectEntity>, SubjectEntityService {
    }
}
