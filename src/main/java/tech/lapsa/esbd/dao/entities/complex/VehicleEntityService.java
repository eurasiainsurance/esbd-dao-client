package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.ICachableEntitiesService;
import tech.lapsa.esbd.domain.complex.VehicleEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

public interface VehicleEntityService extends ICachableEntitiesService<VehicleEntity> {

    public static final String BEAN_NAME = "VehicleEntityServiceBean";

    @Local
    public interface VehicleEntityServiceLocal
	    extends ICachableEntityServiceLocal<VehicleEntity>, VehicleEntityService {
    }

    @Remote
    public interface VehicleEntityServiceRemote
	    extends ICachableEntityServiceRemote<VehicleEntity>, VehicleEntityService {
    }

    List<VehicleEntity> getByVINCode(String vinCode) throws IllegalArgument;

    List<VehicleEntity> getByRegNumber(VehicleRegNumber regNumber) throws IllegalArgument;
}
