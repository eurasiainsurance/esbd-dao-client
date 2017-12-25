package tech.lapsa.esbd.dao.beans.entities;

import java.util.List;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.entities.VehicleManufacturerEntity;
import tech.lapsa.esbd.dao.entities.VehicleModelEntity;
import tech.lapsa.esbd.dao.entities.VehicleModelEntityService;
import tech.lapsa.esbd.dao.entities.VehicleManufacturerEntityService.VehicleManufacturerEntityServiceLocal;
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

@Stateless(name = VehicleModelEntityService.BEAN_NAME)
public class VehicleModelEntityServiceBean implements VehicleModelEntityServiceLocal, VehicleModelEntityServiceRemote {

    @EJB
    private VehicleManufacturerEntityServiceLocal vehicleManufacturerService;

    @EJB
    private ConnectionPool pool;

    @Override
    public VehicleModelEntity getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	try (Connection con = pool.getConnection()) {
	    // VOITURE_MODEL_ID, NAME, VOITURE_MARK_ID
	    final VOITUREMODEL m = new VOITUREMODEL();
	    m.setID(new Long(id).intValue());
	    final ArrayOfVOITUREMODEL models = con.getVoitureModels(m);
	    if (models == null || models.getVOITUREMODEL() == null || models.getVOITUREMODEL().isEmpty())
		throw new NotFound(VehicleModelEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    if (models.getVOITUREMODEL().size() > 1)
		throw new DataCoruptionException("Too many " + VehicleModelEntity.class.getSimpleName() + " ("
			+ models.getVOITUREMODEL().size() + ") with ID = '" + id + "'");
	    return convert(models.getVOITUREMODEL().iterator().next());
	}
    }

    @Override
    public List<VehicleModelEntity> getByName(final String name) throws IllegalArgument {
	MyStrings.requireNonEmpty(IllegalArgument::new, name, "name");
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

    @Override
    public List<VehicleModelEntity> getByManufacturer(final VehicleManufacturerEntity manufacturer)
	    throws IllegalArgument {
	MyObjects.requireNonNull(IllegalArgument::new, manufacturer, "manufacturer");
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
	try {
	    target.setManufacturer(vehicleManufacturerService.getById(source.getVOITUREMARKID()));
	} catch (NotFound | IllegalArgument e) {
	    // mandatory field
	    throw new DataCoruptionException(
		    "Error while fetching Vehicle Manufacturer ID = '" + source.getID()
			    + "' from ESBD. Vehicle Manufacturer ID = '" + source.getVOITUREMARKID() + "' not found",
		    e);
	}
    }

}
