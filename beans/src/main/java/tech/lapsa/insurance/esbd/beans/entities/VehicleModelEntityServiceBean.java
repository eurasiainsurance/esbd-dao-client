package com.lapsa.insurance.esbd.services.impl.entities;

import java.util.List;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfVOITUREMODEL;
import com.lapsa.esbd.jaxws.client.VOITUREMODEL;
import com.lapsa.insurance.esbd.domain.entities.policy.VehicleManufacturerEntity;
import com.lapsa.insurance.esbd.domain.entities.policy.VehicleModelEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.policy.VehicleManufacturerServiceDAO;
import com.lapsa.insurance.esbd.services.policy.VehicleModelServiceDAO;

import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class VehicleModelEntityServiceWS extends AbstractESBDEntityServiceWS implements VehicleModelServiceDAO {

    @EJB
    private VehicleManufacturerServiceDAO vehicleManufacturerService;

    @Inject
    private ESBDConnectionPool pool;

    @Override
    public VehicleModelEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (ESBDConnection con = pool.getConnection()) {
	    // VOITURE_MODEL_ID, NAME, VOITURE_MARK_ID
	    VOITUREMODEL m = new VOITUREMODEL();
	    m.setID(new Long(id).intValue());
	    ArrayOfVOITUREMODEL models = con.getVoitureModels(m);
	    if (models == null || models.getVOITUREMODEL() == null || models.getVOITUREMODEL().isEmpty())
		throw new NotFound(VehicleModelEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    if (models.getVOITUREMODEL().size() > 1)
		throw new DataCoruptionException("Too many " + VehicleModelEntity.class.getSimpleName() + " ("
			+ models.getVOITUREMODEL().size() + ") with ID = '" + id + "'");
	    return convert(models.getVOITUREMODEL().iterator().next());
	}
    }

    @Override
    public List<VehicleModelEntity> getByName(String name) {
	MyStrings.requireNonEmpty(name, "name");
	try (ESBDConnection con = pool.getConnection()) {
	    // VOITURE_MODEL_ID, NAME, VOITURE_MARK_ID
	    // List<VehicleModelEntity> res = new ArrayList<>();
	    VOITUREMODEL m = new VOITUREMODEL();
	    m.setNAME(name);
	    ArrayOfVOITUREMODEL reslist = con.getVoitureModels(m);
	    return MyOptionals.streamOf(reslist.getVOITUREMODEL()) //
		    .orElseGet(Stream::empty) //
		    .map(this::convert)
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    @Override
    public List<VehicleModelEntity> getByManufacturer(VehicleManufacturerEntity manufacturer) {
	MyObjects.requireNonNull(manufacturer, "manufacturer");
	try (ESBDConnection con = pool.getConnection()) {
	    // VOITURE_MODEL_ID, NAME, VOITURE_MARK_ID
	    VOITUREMODEL m = new VOITUREMODEL();
	    m.setVOITUREMARKID(new Long(manufacturer.getId()).intValue());
	    ArrayOfVOITUREMODEL reslist = con.getVoitureModels(m);
	    return MyOptionals.streamOf(reslist.getVOITUREMODEL()) //
		    .orElseGet(Stream::empty) //
		    .map(this::convert)
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    VehicleModelEntity convert(VOITUREMODEL source) {
	VehicleModelEntity vehicle = new VehicleModelEntity();
	fillValues(source, vehicle);
	return vehicle;
    }

    void fillValues(VOITUREMODEL source, VehicleModelEntity target,
	    VehicleManufacturerEntity defaultManufacturer) {
	target.setId(source.getID());
	target.setName(source.getNAME());
	target.setManufacturer(defaultManufacturer);
    }

    void fillValues(VOITUREMODEL source, VehicleModelEntity target) {
	target.setId(source.getID());
	target.setName(source.getNAME());
	try {
	    target.setManufacturer(vehicleManufacturerService.getById(new Long(source.getVOITUREMARKID())));
	} catch (NotFound e) {
	    // mandatory field
	    throw new DataCoruptionException(
		    "Error while fetching Vehicle Manufacturer ID = '" + source.getID()
			    + "' from ESBD. Vehicle Manufacturer ID = '" + source.getVOITUREMARKID() + "' not found",
		    e);
	}
    }

}
