package tech.lapsa.esbd.dao.beans.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.lapsa.insurance.elements.SteeringWheelLocation;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.VehicleClassService.VehicleClassServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleEntity;
import tech.lapsa.esbd.dao.entities.VehicleEntityService;
import tech.lapsa.esbd.dao.entities.VehicleEntityService.VehicleEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleEntityService.VehicleEntityServiceRemote;
import tech.lapsa.esbd.dao.entities.VehicleModelEntity;
import tech.lapsa.esbd.dao.entities.VehicleModelEntityService.VehicleModelEntityServiceLocal;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfTF;
import tech.lapsa.esbd.jaxws.wsimport.TF;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyCollectors;
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
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public VehicleEntity getById(final Integer id) throws NotFound, IllegalArgument {
	try {
	    return _getById(id);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<VehicleEntity> getByVINCode(final String vinCode) throws IllegalArgument {
	try {
	    return _getByVINCode(vinCode);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
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
	try (Connection con = pool.getConnection()) {
	    final ArrayOfTF vehicles = con.getTFByNumber(regNumber.getNumber());
	    return MyOptionals.of(vehicles) //
		    .map(ArrayOfTF::getTF) //
		    .map(Collection::stream) //
		    .orElseGet(Stream::empty) //
		    .map(this::convert) //
		    .peek(x -> x.setRegNum(regNumber.getNumber()))
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    private VehicleEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (Connection con = pool.getConnection()) {
	    final TF tf = new TF();
	    tf.setTFID(new Long(id).intValue());
	    final ArrayOfTF vehicles = con.getTFByKeyFields(tf);
	    if (vehicles == null || vehicles.getTF() == null || vehicles.getTF().isEmpty())
		throw new NotFound(VehicleEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    final TF source = Util.requireSingle(vehicles.getTF(), VehicleEntity.class, "ID", id);
	    return convert(source);
	}
    }

    private List<VehicleEntity> _getByVINCode(final String vinCode) throws IllegalArgumentException {
	MyStrings.requireNonEmpty(vinCode, "vinCode");
	try (Connection con = pool.getConnection()) {
	    final ArrayOfTF vehicles = con.getTFByVIN(vinCode);
	    return MyOptionals.of(vehicles) //
		    .map(ArrayOfTF::getTF) //
		    .map(Collection::stream) //
		    .orElseGet(Stream::empty) //
		    .map(this::convert) //
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    VehicleEntity convert(final TF source) {
	final VehicleEntity target = new VehicleEntity();
	fillValues(source, target);
	return target;
    }

    void fillValues(final TF source, final VehicleEntity target) {

	// TF_ID s:int Идентификатор ТС
	target.setId(source.getTFID());

	// TF_TYPE_ID s:int Тип ТС (справочник TF_TYPES)
	// non mandatory field
	target.setVehicleClass(MyOptionals.of(source.getTFTYPEID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> vehicleClassService.getById(id))) //
		.orElse(null));

	// VIN s:string VIN код (номер кузова) (обязательно)
	target.setVinCode(source.getVIN());

	// MODEL_ID s:int Марка\Модель (справочник VOITURE_MODELS) (обязательно)
	Util.requireField(target, target.getId(), vehicleModelService::getById, target::setVehicleModel,
		"VehicleModel", VehicleModelEntity.class, source.getMODELID());

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
	    final LocalDate date = fromESBDYearMonth(source.getBORN(), source.getBORNMONTH());
	    target.setRealeaseDate(date);
	}
    }

    private LocalDate fromESBDYearMonth(final String yearS, final int month) {
	try {
	    final int year = Integer.parseInt(yearS);
	    if (year < 1000 || year > 9999)
		return null;
	    if (month == 0)
		return null;
	    return LocalDate.of(year, month, 1);
	} catch (final NumberFormatException e) {
	    return null;
	}
    }
}
