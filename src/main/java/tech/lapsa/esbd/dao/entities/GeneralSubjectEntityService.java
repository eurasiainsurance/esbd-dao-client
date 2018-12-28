package tech.lapsa.esbd.dao.entities;

import java.util.List;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.domain.entities.SubjectEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface GeneralSubjectEntityService<T extends SubjectEntity> extends GeneralEntityService<T, Integer> {

    public interface GeneralSubjectEntityServiceLocal<T extends SubjectEntity>
	    extends GeneralEntityServiceLocal<T, Integer>, GeneralSubjectEntityService<T> {
    }

    public interface GeneralSubjectEntityServiceRemote<T extends SubjectEntity>
	    extends GeneralEntityServiceRemote<T, Integer>, GeneralSubjectEntityService<T> {
    }

    List<T> getByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument;

    T getFirstByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;
}
