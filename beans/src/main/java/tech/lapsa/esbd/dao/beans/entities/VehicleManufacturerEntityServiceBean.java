package tech.lapsa.esbd.dao.beans.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.entities.VehicleManufacturerEntity;
import tech.lapsa.esbd.dao.entities.VehicleManufacturerEntityService;
import tech.lapsa.esbd.dao.entities.VehicleManufacturerEntityService.VehicleManufacturerEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.VehicleManufacturerEntityService.VehicleManufacturerEntityServiceRemote;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfVOITUREMARK;
import tech.lapsa.esbd.jaxws.wsimport.VOITUREMARK;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.logging.MyLogger;

@Stateless(name = VehicleManufacturerEntityService.BEAN_NAME)
public class VehicleManufacturerEntityServiceBean
	implements VehicleManufacturerEntityServiceLocal, VehicleManufacturerEntityServiceRemote {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(VehicleManufacturerEntityService.class) //
	    .build();

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public VehicleManufacturerEntity getById(final Integer id) throws NotFound, IllegalArgument {
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
    public List<VehicleManufacturerEntity> getByName(final String name) throws IllegalArgument {
	try {
	    return _getByName(name);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    @EJB
    private ConnectionPool pool;

    private VehicleManufacturerEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (Connection con = pool.getConnection()) {
	    final VOITUREMARK m = new VOITUREMARK();
	    m.setID(new Long(id).intValue());
	    final ArrayOfVOITUREMARK manufacturers = con.getVoitureMarks(m);
	    if (manufacturers == null || manufacturers.getVOITUREMARK() == null
		    || manufacturers.getVOITUREMARK().isEmpty())
		throw new NotFound(
			VehicleManufacturerEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    final VOITUREMARK source = Util.requireSingle(manufacturers.getVOITUREMARK(),
		    VehicleManufacturerEntity.class, "ID", id);
	    return convert(source);
	}
    }

    private List<VehicleManufacturerEntity> _getByName(final String name) throws IllegalArgumentException {
	MyStrings.requireNonEmpty(name, "name");
	try (Connection con = pool.getConnection()) {
	    final List<VehicleManufacturerEntity> res = new ArrayList<>();
	    final VOITUREMARK m = new VOITUREMARK();
	    m.setNAME(name);
	    final ArrayOfVOITUREMARK manufacturers = con.getVoitureMarks(m);
	    if (manufacturers == null || manufacturers.getVOITUREMARK() == null
		    || manufacturers.getVOITUREMARK().isEmpty())
		return res;
	    for (final VOITUREMARK source : manufacturers.getVOITUREMARK()) {
		final VehicleManufacturerEntity e = new VehicleManufacturerEntity();
		fillValues(source, e);
		res.add(e);
	    }
	    return res;
	}
    }

    VehicleManufacturerEntity convert(final VOITUREMARK source) {
	final VehicleManufacturerEntity manufacturer = new VehicleManufacturerEntity();
	fillValues(source, manufacturer);
	return manufacturer;
    }

    void fillValues(final VOITUREMARK source, final VehicleManufacturerEntity target) {
	target.setId(source.getID());
	target.setName(source.getNAME());
	target.setForeign(source.getISFOREIGNBOOL() != 0);
    }
}
