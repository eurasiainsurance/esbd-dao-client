package com.lapsa.insurance.esbd.services.impl.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfItem;
import com.lapsa.esbd.jaxws.client.Item;
import com.lapsa.insurance.esbd.domain.entities.general.CompanyActivityKindEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.general.CompanyActivityKindServiceDAO;

import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;

@Stateless
public class CompanyActivityKindEntityServiceWS extends AbstractESBDEntityServiceWS
	implements CompanyActivityKindServiceDAO {

    private static final String DICT_NAME = "ACTIVITY_KINDS";

    private List<CompanyActivityKindEntity> all;

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
    public List<CompanyActivityKindEntity> getAll() {
	lazyInit();
	return new ArrayList<>(all);
    }

    @Override
    public CompanyActivityKindEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	lazyInit();
	for (CompanyActivityKindEntity be : all)
	    if (be.getId() == id)
		return be;
	throw new NotFound(CompanyActivityKindEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
    }

    CompanyActivityKindEntity convert(Item source) {
	CompanyActivityKindEntity target = new CompanyActivityKindEntity();
	fillValues(source, target);
	return target;
    }

    void fillValues(Item source, CompanyActivityKindEntity target) {
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
    }

}
