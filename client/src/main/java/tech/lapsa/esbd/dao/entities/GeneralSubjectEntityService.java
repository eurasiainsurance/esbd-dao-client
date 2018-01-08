package tech.lapsa.esbd.dao.entities;

import java.util.List;

import tech.lapsa.esbd.dao.GeneralService;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface GeneralSubjectEntityService<T extends SubjectEntity> extends GeneralService<T, Integer> {

    public interface GeneralSubjectEntityServiceLocal<T extends SubjectEntity>
	    extends GeneralServiceLocal<T, Integer>, GeneralSubjectEntityService<T> {
    }

    public interface GeneralSubjectEntityServiceRemote<T extends SubjectEntity>
	    extends GeneralServiceRemote<T, Integer>, GeneralSubjectEntityService<T> {
    }

    List<T> getByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument;

    T getFirstByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;
}
