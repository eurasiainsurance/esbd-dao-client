package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.entities.ICachableEntitiesService;
import tech.lapsa.esbd.domain.complex.SubjectEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

public interface ISubjectEntitiesService<T extends SubjectEntity> extends ICachableEntitiesService<T> {

    public interface ISubjectEntityServiceLocal<T extends SubjectEntity>
	    extends ICachableEntityServiceLocal<T>, ISubjectEntitiesService<T> {
    }

    public interface ISubjectEntityServiceRemote<T extends SubjectEntity>
	    extends ICachableEntityServiceRemote<T>, ISubjectEntitiesService<T> {
    }

    List<T> getByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument;

    T getFirstByIdNumber(TaxpayerNumber taxpayerNumber) throws IllegalArgument, NotFound;
}
