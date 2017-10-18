package tech.lapsa.insurance.esbd.beans.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfVOITUREMARK;
import com.lapsa.esbd.jaxws.client.VOITUREMARK;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.VehicleManufacturerEntity;
import tech.lapsa.insurance.esbd.entities.VehicleManufacturerEntityService;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class VehicleManufacturerEntityServiceBean implements VehicleManufacturerEntityService {

    @Inject
    private ESBDConnectionPool pool;

    @Override
    public VehicleManufacturerEntity getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	try (ESBDConnection con = pool.getConnection()) {
	    VOITUREMARK m = new VOITUREMARK();
	    m.setID(new Long(id).intValue());
	    ArrayOfVOITUREMARK manufacturers = con.getVoitureMarks(m);
	    if (manufacturers == null || manufacturers.getVOITUREMARK() == null
		    || manufacturers.getVOITUREMARK().isEmpty())
		throw new NotFound(
			VehicleManufacturerEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    if (manufacturers.getVOITUREMARK().size() > 1)
		throw new DataCoruptionException("Too many " + VehicleManufacturerEntity.class.getSimpleName() + " ("
			+ manufacturers.getVOITUREMARK().size() + ") with ID = '" + id + "'");

	    return convert(manufacturers.getVOITUREMARK().iterator().next());
	}
    }

    @Override
    public List<VehicleManufacturerEntity> getByName(String name) {
	MyStrings.requireNonEmpty(name, "name");
	try (ESBDConnection con = pool.getConnection()) {
	    List<VehicleManufacturerEntity> res = new ArrayList<>();
	    VOITUREMARK m = new VOITUREMARK();
	    m.setNAME(name);
	    ArrayOfVOITUREMARK manufacturers = con.getVoitureMarks(m);
	    if (manufacturers == null || manufacturers.getVOITUREMARK() == null
		    || manufacturers.getVOITUREMARK().isEmpty())
		return res;
	    for (VOITUREMARK source : manufacturers.getVOITUREMARK()) {
		VehicleManufacturerEntity e = new VehicleManufacturerEntity();
		fillValues(source, e);
		res.add(e);
	    }
	    return res;
	}
    }

    VehicleManufacturerEntity convert(VOITUREMARK source) {
	VehicleManufacturerEntity manufacturer = new VehicleManufacturerEntity();
	fillValues(source, manufacturer);
	return manufacturer;
    }

    void fillValues(VOITUREMARK source, VehicleManufacturerEntity target) {
	target.setId(source.getID());
	target.setName(source.getNAME());
	target.setForeign(source.getISFOREIGNBOOL() != 0);
    }
}
