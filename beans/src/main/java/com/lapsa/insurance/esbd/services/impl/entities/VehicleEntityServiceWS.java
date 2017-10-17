package com.lapsa.insurance.esbd.services.impl.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfTF;
import com.lapsa.esbd.jaxws.client.TF;
import com.lapsa.insurance.elements.SteeringWheelLocation;
import com.lapsa.insurance.esbd.domain.entities.policy.VehicleEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.VehicleClassServiceDAO;
import com.lapsa.insurance.esbd.services.policy.VehicleModelServiceDAO;
import com.lapsa.insurance.esbd.services.policy.VehicleServiceDAO;

import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class VehicleEntityServiceWS extends AbstractESBDEntityServiceWS implements VehicleServiceDAO {

    @EJB
    private VehicleClassServiceDAO vehicleClassService;

    @EJB
    private VehicleModelServiceDAO vehicleModelService;

    @Inject
    private ESBDConnectionPool pool;

    @Override
    public List<VehicleEntity> getByRegNumber(String regNumber) {
	MyStrings.requireNonEmpty(regNumber, "regNumber");
	try (ESBDConnection con = pool.getConnection()) {
	    ArrayOfTF vehicles = con.getTFByNumber(regNumber);
	    return MyOptionals.of(vehicles) //
		    .map(ArrayOfTF::getTF) //
		    .map(Collection::stream) //
		    .orElseGet(Stream::empty) //
		    .map(this::convert) //
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    @Override
    public VehicleEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (ESBDConnection con = pool.getConnection()) {
	    TF tf = new TF();
	    tf.setTFID(new Long(id).intValue());
	    ArrayOfTF vehicles = con.getTFByKeyFields(tf);
	    if (vehicles == null || vehicles.getTF() == null || vehicles.getTF().isEmpty())
		throw new NotFound(VehicleEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    if (vehicles.getTF().size() > 1)
		throw new DataCoruptionException("Too many " + VehicleEntity.class.getSimpleName() + " ("
			+ vehicles.getTF().size() + ") with ID = '" + id + "'");
	    return convert(vehicles.getTF().iterator().next());
	}
    }

    @Override
    public List<VehicleEntity> getByVINCode(String vinCode) {
	MyStrings.requireNonEmpty(vinCode, "vinCode");
	try (ESBDConnection con = pool.getConnection()) {
	    ArrayOfTF vehicles = con.getTFByVIN(vinCode);
	    return MyOptionals.of(vehicles) //
		    .map(ArrayOfTF::getTF) //
		    .map(Collection::stream) //
		    .orElseGet(Stream::empty) //
		    .map(this::convert) //
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    VehicleEntity convert(TF source) {
	VehicleEntity target = new VehicleEntity();
	fillValues(source, target);
	return target;
    }

    void fillValues(TF source, VehicleEntity target) {

	// TF_ID s:int Идентификатор ТС
	target.setId(source.getTFID());

	// TF_TYPE_ID s:int Тип ТС (справочник TF_TYPES)
	try {
	    if (MyNumbers.nonZero(source.getTFTYPEID()))
		target.setVehicleClass(vehicleClassService.getById(source.getTFTYPEID()));
	} catch (NotFound e) {
	    // non mandatory field
	}

	// VIN s:string VIN код (номер кузова) (обязательно)
	target.setVinCode(source.getVIN());

	// MODEL_ID s:int Марка\Модель (справочник VOITURE_MODELS) (обязательно)
	try {
	    target.setVehicleModel(vehicleModelService.getById(new Long(source.getMODELID())));
	} catch (NotFound e) {
	    // mandatory field
	    throw new DataCoruptionException("Error while fetching Vehicle ID = '" + source.getTFID()
		    + "' from ESBD. VehicleModel ID = '" + source.getMODELID() + "' not found", e);
	}

	// RIGHT_HAND_DRIVE_BOOL s:int Признак расположения руля (0 - слева; 1 -
	// справа)
	target.setSteeringWheelLocation(source.getRIGHTHANDDRIVEBOOL() == 0 ? SteeringWheelLocation.LEFT_SIDE
		: SteeringWheelLocation.RIGHT_SIDE);

	// ENGINE_VOLUME s:int Объем двигателя (куб.см.)
	target.setEngineVolume(source.getENGINEVOLUME());

	// ENGINE_NUMBER s:string Номер двигателя
	target.setEnineNumber(source.getENGINENUMBER());

	// ENGINE_POWER s:int Мощность двигателя (квт.)
	target.setEnginePower(source.getENGINEPOWER());

	// COLOR s:string Цвет ТС
	target.setColor(source.getCOLOR());

	// BORN s:string Год выпуска (обязательно)
	// BORN_MONTH s:int Месяц выпуска ТС
	{
	    LocalDate date = fromESBDYearMonth(source.getBORN(), source.getBORNMONTH());
	    target.setRealeaseDate(date);
	}
    }

    private LocalDate fromESBDYearMonth(String yearS, int month) {
	try {
	    int year = Integer.parseInt(yearS);
	    if (year < 1000 || year > 9999)
		return null;
	    if (month == 0)
		return null;
	    return LocalDate.of(year, month, 1);
	} catch (NumberFormatException e) {
	    return null;
	}
    }
}
