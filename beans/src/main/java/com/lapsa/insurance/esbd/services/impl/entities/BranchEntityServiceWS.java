package com.lapsa.insurance.esbd.services.impl.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfItem;
import com.lapsa.esbd.jaxws.client.Item;
import com.lapsa.insurance.esbd.domain.entities.general.BranchEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.general.BranchServiceDAO;

import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;

@Stateless
public class BranchEntityServiceWS extends AbstractESBDEntityServiceWS implements BranchServiceDAO {

    private static final String DICT_NAME = "BRANCHES";

    private List<BranchEntity> all;

    @Inject
    private ESBDConnectionPool pool;

    private void lazyInit() {
	if (all != null)
	    return;

	try (ESBDConnection con = pool.getConnection()) {
	    ArrayOfItem items = con.getItems(DICT_NAME);
	    if (items == null)
		return;
	    all = items.getItem().stream() //
		    .map(this::convert) //
		    .collect(MyCollectors.unmodifiableList());
	}
    }

    @Override
    public BranchEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	lazyInit();
	for (BranchEntity be : all)
	    if (be.getId() == id)
		return be;
	throw new NotFound(BranchEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
    }

    BranchEntity convert(Item source) {
	BranchEntity target = new BranchEntity();
	fillValues(source, target);
	return target;
    }

    void fillValues(Item source, BranchEntity target) {
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
    }

    @Override
    public List<BranchEntity> getAll() {
	lazyInit();
	return new ArrayList<BranchEntity>(all);
    }

}
