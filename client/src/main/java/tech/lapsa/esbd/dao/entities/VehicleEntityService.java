package tech.lapsa.esbd.dao.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.esbd.dao.GeneralService;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

public interface VehicleEntityService extends GeneralService<VehicleEntity, Integer> {

    public static final String BEAN_NAME = "VehicleEntityServiceBean";

    @Local
    public interface VehicleEntityServiceLocal
	    extends GeneralServiceLocal<VehicleEntity, Integer>, VehicleEntityService {
    }

    @Remote
    public interface VehicleEntityServiceRemote
	    extends GeneralServiceRemote<VehicleEntity, Integer>, VehicleEntityService {
    }

    List<VehicleEntity> getByVINCode(String vinCode) throws IllegalArgument;

    List<VehicleEntity> getByRegNumber(VehicleRegNumber regNumber) throws IllegalArgument;
}
