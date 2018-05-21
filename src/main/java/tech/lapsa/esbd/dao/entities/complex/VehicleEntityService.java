package tech.lapsa.esbd.dao.entities.complex;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.entities.AEntityService;
import tech.lapsa.esbd.domain.complex.VehicleEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

public interface VehicleEntityService extends AEntityService<VehicleEntity, Integer> {

    public static final String BEAN_NAME = "VehicleEntityServiceBean";

    @Local
    public interface VehicleEntityServiceLocal
	    extends AEntityServiceLocal<VehicleEntity, Integer>, VehicleEntityService {
    }

    @Remote
    public interface VehicleEntityServiceRemote
	    extends AEntityServiceRemote<VehicleEntity, Integer>, VehicleEntityService {
    }

    List<VehicleEntity> getByVINCode(String vinCode) throws IllegalArgument;

    List<VehicleEntity> getByRegNumber(VehicleRegNumber regNumber) throws IllegalArgument;
}
