package tech.lapsa.esbd.dao.entities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.lapsa.insurance.elements.SteeringWheelLocation;
import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.beans.ESBDDates;
import tech.lapsa.esbd.dao.beans.entities.Util;
import tech.lapsa.esbd.dao.elements.VehicleClassService.VehicleClassServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleEntityService.VehicleEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleEntityService.VehicleEntityServiceRemote;
import tech.lapsa.esbd.dao.entities.VehicleModelEntityService.VehicleModelEntityServiceLocal;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfTF;
import tech.lapsa.esbd.jaxws.wsimport.TF;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

@Stateless(name = VehicleEntityService.BEAN_NAME)
public class VehicleEntityServiceBean implements VehicleEntityServiceLocal, VehicleEntityServiceRemote {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(VehicleEntityService.class) //
	    .build();

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<VehicleEntity> getByRegNumber(final VehicleRegNumber regNumber) throws IllegalArgument {
	try {
	    return _getByRegNumber(regNumber);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public VehicleEntity getById(final Integer id) throws NotFound, IllegalArgument {
	try {
	    return _getById(id);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<VehicleEntity> getByVINCode(final String vinCode) throws IllegalArgument {
	try {
	    return _getByVINCode(vinCode);
	} catch (final IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (final RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    @EJB
    private VehicleClassServiceLocal vehicleClassService;

    @EJB
    private VehicleModelEntityServiceLocal vehicleModelService;

    @EJB
    private ConnectionPool pool;

    private List<VehicleEntity> _getByRegNumber(final VehicleRegNumber regNumber) throws IllegalArgumentException {
	MyObjects.requireNonNull(regNumber, "regNumber"); //
	VehicleRegNumber.requireValid(regNumber);

	final ArrayOfTF vehicles;
	try (Connection con = pool.getConnection()) {
	    vehicles = con.getTFByNumber(regNumber.getNumber());

	}
	return MyOptionals.of(vehicles) //
		.map(ArrayOfTF::getTF) //
		.map(Collection::stream) //
		.orElseGet(Stream::empty) //
		.map(this::convert) //
		.peek(x -> x.setRegNum(regNumber))
		.collect(MyCollectors.unmodifiableList());
    }

    private VehicleEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	final ArrayOfTF vehicles;
	try (Connection con = pool.getConnection()) {
	    final TF tf = new TF();
	    tf.setTFID(id.intValue());
	    vehicles = con.getTFByKeyFields(tf);
	}
	final List<TF> ltf = MyOptionals.of(vehicles) //
		.map(ArrayOfTF::getTF) //
		.orElseThrow(MyExceptions.supplier(NotFound::new, "%1$s not found with ID = '%2$s'",
			VehicleEntity.class.getSimpleName(), id));
	final TF source = Util.requireSingle(ltf, VehicleEntity.class, "ID", id);
	return convert(source);
    }

    private List<VehicleEntity> _getByVINCode(final String vinCode) throws IllegalArgumentException {
	MyStrings.requireNonEmpty(vinCode, "vinCode");
	final ArrayOfTF vehicles;
	try (Connection con = pool.getConnection()) {
	    vehicles = con.getTFByVIN(vinCode);
	}
	return MyOptionals.of(vehicles) //
		.map(ArrayOfTF::getTF) //
		.map(Collection::stream) //
		.orElseGet(Stream::empty) //
		.map(this::convert) //
		.collect(MyCollectors.unmodifiableList());
    }

    VehicleEntity convert(final TF source) {
	final VehicleEntity target = new VehicleEntity();
	fillValues(source, target);
	return target;
    }

    void fillValues(final TF source, final VehicleEntity target) {

	// TF_ID s:int Идентификатор ТС
	target.id = MyOptionals.of(source.getTFID()).orElse(null);

	// TF_TYPE_ID s:int Тип ТС (справочник TF_TYPES)
	// non mandatory field
	target.vehicleClassId = source.getTFTYPEID();
	Util.requireField(target, target.id, vehicleClassService::getById, target::setVehicleClass, "VehicleClass",
		VehicleClass.class, target.vehicleClassId);

	// VIN s:string VIN код (номер кузова) (обязательно)
	target.vinCode = source.getVIN();

	// MODEL_ID s:int Марка\Модель (справочник VOITURE_MODELS) (обязательно)
	target.vehicleModelId = source.getMODELID();
	Util.requireField(target, target.getId(), vehicleModelService::getById, target::setVehicleModel,
		"VehicleModel", VehicleModelEntity.class, target.vehicleModelId);

	// RIGHT_HAND_DRIVE_BOOL s:int Признак расположения руля (0 - слева; 1 -
	// справа)
	target.steeringWheelLocation = source.getRIGHTHANDDRIVEBOOL() == 0
		? SteeringWheelLocation.LEFT_SIDE
		: SteeringWheelLocation.RIGHT_SIDE;

	// ENGINE_VOLUME s:int Объем двигателя (куб.см.)
	target.enginePower = source.getENGINEVOLUME();

	// ENGINE_NUMBER s:string Номер двигателя
	target.enineNumber = source.getENGINENUMBER();

	// ENGINE_POWER s:int Мощность двигателя (квт.)
	target.enginePower = source.getENGINEPOWER();

	// COLOR s:string Цвет ТС
	target.color = source.getCOLOR();

	// BORN s:string Год выпуска (обязательно)
	// BORN_MONTH s:int Месяц выпуска ТС
	target.realeaseDate = ESBDDates.fromESBDYearMonth(source.getBORN(), source.getBORNMONTH());
    }

}
