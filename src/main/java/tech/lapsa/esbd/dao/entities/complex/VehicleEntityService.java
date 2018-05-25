package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.IEntitiesService;
import tech.lapsa.esbd.domain.complex.VehicleEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

public interface VehicleEntityService extends IEntitiesService<VehicleEntity> {

    public static final String BEAN_NAME = "VehicleEntityServiceBean";

    @Local
    public interface VehicleEntityServiceLocal
	    extends IEntityServiceLocal<VehicleEntity>, VehicleEntityService {
    }

    @Remote
    public interface VehicleEntityServiceRemote
	    extends IEntityServiceRemote<VehicleEntity>, VehicleEntityService {
    }

    List<VehicleEntity> getByVINCode(String vinCode) throws IllegalArgument;

    List<VehicleEntity> getByRegNumber(VehicleRegNumber regNumber) throws IllegalArgument;
}
