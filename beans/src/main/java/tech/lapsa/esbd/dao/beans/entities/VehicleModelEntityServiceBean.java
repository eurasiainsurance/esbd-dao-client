package tech.lapsa.esbd.dao.beans.entities;

import java.util.List;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.entities.VehicleManufacturerEntity;
import tech.lapsa.esbd.dao.entities.VehicleManufacturerEntityService.VehicleManufacturerEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleModelEntity;
import tech.lapsa.esbd.dao.entities.VehicleModelEntityService;
import tech.lapsa.esbd.dao.entities.VehicleModelEntityService.VehicleModelEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleModelEntityService.VehicleModelEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfVOITUREMODEL;
import tech.lapsa.esbd.jaxws.wsimport.VOITUREMODEL;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.logging.MyLogger;

@Stateless(name = VehicleModelEntityService.BEAN_NAME)
public class VehicleModelEntityServiceBean implements VehicleModelEntityServiceLocal, VehicleModelEntityServiceRemote {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(VehicleModelEntityService.class) //
	    .build();

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public VehicleModelEntity getById(final Integer id) throws NotFound, IllegalArgument {
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
    public List<VehicleModelEntity> getByName(final String name) throws IllegalArgument {
	try {
	    return _getByName(name);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<VehicleModelEntity> getByManufacturer(final VehicleManufacturerEntity manufacturer)
	    throws IllegalArgument {
	try {
	    return _getByManufacturer(manufacturer);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    @EJB
    private VehicleManufacturerEntityServiceLocal vehicleManufacturerService;

    @EJB
    private ConnectionPool pool;

    private VehicleModelEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (Connection con = pool.getConnection()) {
	    // VOITURE_MODEL_ID, NAME, VOITURE_MARK_ID
	    final VOITUREMODEL m = new VOITUREMODEL();
	    m.setID(new Long(id).intValue());
	    final ArrayOfVOITUREMODEL models = con.getVoitureModels(m);
	    if (models == null || models.getVOITUREMODEL() == null || models.getVOITUREMODEL().isEmpty())
		throw new NotFound(VehicleModelEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    Util.requireSingle(models.getVOITUREMODEL(), VehicleModelEntity.class, "ID", id);
	    return convert(models.getVOITUREMODEL().iterator().next());
	}
    }

    private List<VehicleModelEntity> _getByName(final String name) throws IllegalArgumentException {
	MyStrings.requireNonEmpty(name, "name");
	try (Connection con = pool.getConnection()) {
	    // VOITURE_MODEL_ID, NAME, VOITURE_MARK_ID
	    // List<VehicleModelEntity> res = new ArrayList<>();
	    final VOITUREMODEL m = new VOITUREMODEL();
	    m.setNAME(name);
	    final ArrayOfVOITUREMODEL reslist = con.getVoitureModels(m);
	    return MyOptionals.streamOf(reslist.getVOITUREMODEL()) //
		    .orElseGet(Stream::empty) //
		    .map(this::convert)
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    private List<VehicleModelEntity> _getByManufacturer(final VehicleManufacturerEntity manufacturer)
	    throws IllegalArgumentException {
	MyObjects.requireNonNull(manufacturer, "manufacturer");
	try (Connection con = pool.getConnection()) {
	    // VOITURE_MODEL_ID, NAME, VOITURE_MARK_ID
	    final VOITUREMODEL m = new VOITUREMODEL();
	    m.setVOITUREMARKID(new Long(manufacturer.getId()).intValue());
	    final ArrayOfVOITUREMODEL reslist = con.getVoitureModels(m);
	    return MyOptionals.streamOf(reslist.getVOITUREMODEL()) //
		    .orElseGet(Stream::empty) //
		    .map(this::convert)
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    VehicleModelEntity convert(final VOITUREMODEL source) {
	final VehicleModelEntity vehicle = new VehicleModelEntity();
	fillValues(source, vehicle);
	return vehicle;
    }

    void fillValues(final VOITUREMODEL source, final VehicleModelEntity target,
	    final VehicleManufacturerEntity defaultManufacturer) {
	target.setId(source.getID());
	target.setName(source.getNAME());
	target.setManufacturer(defaultManufacturer);
    }

    void fillValues(final VOITUREMODEL source, final VehicleModelEntity target) {
	target.setId(source.getID());
	target.setName(source.getNAME());
	Util.requireField(target, target.getId(), vehicleManufacturerService::getById,
		target::setManufacturer, "Manufacturer", VehicleManufacturerEntity.class, source.getVOITUREMARKID());
    }

}
