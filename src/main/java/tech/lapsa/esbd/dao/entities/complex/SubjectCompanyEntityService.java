package tech.lapsa.esbd.dao.entities.complex;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface SubjectCompanyEntityService extends GeneralSubjectEntityService<SubjectCompanyEntity> {

    public static final String BEAN_NAME = "SubjectCompanyEntityServiceBean";

    @Local
    public interface SubjectCompanyEntityServiceLocal
	    extends GeneralSubjectEntityServiceLocal<SubjectCompanyEntity>, SubjectCompanyEntityService {
    }

    @Remote
    public interface SubjectCompanyEntityServiceRemote
	    extends GeneralSubjectEntityServiceRemote<SubjectCompanyEntity>, SubjectCompanyEntityService {
    }
}
