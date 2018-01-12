package tech.lapsa.esbd.dao.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

public interface VehicleEntityService extends GeneralEntityService<VehicleEntity, Integer> {

    public static final String BEAN_NAME = "VehicleEntityServiceBean";

    @Local
    public interface VehicleEntityServiceLocal
	    extends GeneralEntityServiceLocal<VehicleEntity, Integer>, VehicleEntityService {
    }

    @Remote
    public interface VehicleEntityServiceRemote
	    extends GeneralEntityServiceRemote<VehicleEntity, Integer>, VehicleEntityService {
    }

    List<VehicleEntity> getByVINCode(String vinCode) throws IllegalArgument;

    List<VehicleEntity> getByRegNumber(VehicleRegNumber regNumber) throws IllegalArgument;
}
