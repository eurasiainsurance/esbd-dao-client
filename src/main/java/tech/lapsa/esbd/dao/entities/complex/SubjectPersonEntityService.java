package tech.lapsa.esbd.dao.entities.complex;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.domain.complex.SubjectPersonEntity;

public interface SubjectPersonEntityService extends ISubjectEntitiesService<SubjectPersonEntity> {

    public static final String BEAN_NAME = "SubjectPersonEntityServiceBean";

    @Local
    public interface SubjectPersonEntityServiceLocal
	    extends ISubjectEntityServiceLocal<SubjectPersonEntity>, SubjectPersonEntityService {
    }

    @Remote
    public interface SubjectPersonEntityServiceRemote
	    extends ISubjectEntityServiceRemote<SubjectPersonEntity>, SubjectPersonEntityService {
    }
}
