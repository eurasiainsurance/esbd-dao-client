package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.entities.AEntityService;
import tech.lapsa.esbd.domain.complex.SubjectEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface GeneralSubjectEntityService<T extends SubjectEntity> extends AEntityService<T, Integer> {

    public interface GeneralSubjectEntityServiceLocal<T extends SubjectEntity>
	    extends AEntityServiceLocal<T, Integer>, GeneralSubjectEntityService<T> {
    }

    public interface GeneralSubjectEntityServiceRemote<T extends SubjectEntity>
	    extends AEntityServiceRemote<T, Integer>, GeneralSubjectEntityService<T> {
    }

    List<T> getByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument;

    T getFirstByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;
}
