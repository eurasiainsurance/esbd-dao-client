package tech.lapsa.esbd.dao.beans.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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

@Stateless(name = VehicleManufacturerEntityService.BEAN_NAME)
public class VehicleManufacturerEntityServiceBean
	implements VehicleManufacturerEntityServiceLocal, VehicleManufacturerEntityServiceRemote {

    @EJB
    private ConnectionPool pool;

    @Override
    public VehicleManufacturerEntity getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	try (Connection con = pool.getConnection()) {
	    final VOITUREMARK m = new VOITUREMARK();
	    m.setID(new Long(id).intValue());
	    final ArrayOfVOITUREMARK manufacturers = con.getVoitureMarks(m);
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
    public List<VehicleManufacturerEntity> getByName(final String name) throws IllegalArgument {
	MyStrings.requireNonEmpty(IllegalArgument::new, name, "name");
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
