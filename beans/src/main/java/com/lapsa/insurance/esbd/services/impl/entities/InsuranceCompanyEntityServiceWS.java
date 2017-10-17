package com.lapsa.insurance.esbd.services.impl.entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lapsa.esbd.connection.pool.ESBDConnection;
import com.lapsa.esbd.connection.pool.ESBDConnectionPool;
import com.lapsa.esbd.jaxws.client.ArrayOfItem;
import com.lapsa.esbd.jaxws.client.Item;
import com.lapsa.insurance.esbd.domain.entities.general.InsuranceCompanyEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.general.InsuranceCompanyServiceDAO;

import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyNumbers;

@Stateless
public class InsuranceCompanyEntityServiceWS extends AbstractESBDEntityServiceWS implements InsuranceCompanyServiceDAO {

    private static final String DICT_NAME = "INSURANCE_COMPANIES";

    private List<InsuranceCompanyEntity> all;

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
    public List<InsuranceCompanyEntity> getAll() {
	lazyInit();
	return new ArrayList<>(all);
    }

    @Override
    public InsuranceCompanyEntity getById(Long id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");
	lazyInit();
	for (InsuranceCompanyEntity be : all)
	    if (be.getId() == id)
		return be;
	throw new NotFound(InsuranceCompanyEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
    }

    InsuranceCompanyEntity convert(Item source) {
	InsuranceCompanyEntity target = new InsuranceCompanyEntity();
	fillValues(source, target);
	return target;
    }

    void fillValues(Item source, InsuranceCompanyEntity target) {
	target.setId(source.getID());
	target.setCode(source.getCode());
	target.setName(source.getName());
    }

}
