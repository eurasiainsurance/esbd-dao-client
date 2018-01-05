package tech.lapsa.esbd.dao.entities;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface SubjectEntityService extends GeneralSubjectEntityService<SubjectEntity> {

    public static final String BEAN_NAME = "SubjectEntityServiceBean";

    @Local
    public interface SubjectEntityServiceLocal extends GeneralSubjectEntityServiceLocal<SubjectEntity> {
    }

    @Remote
    public interface SubjectEntityServiceRemote extends GeneralSubjectEntityServiceRemote<SubjectEntity> {
    }
}
