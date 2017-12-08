package test.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntity;
import tech.lapsa.insurance.esbd.dict.InsuranceCompanyEntityService;
import test.ArquillianBaseTestCase;

public class InsuranceCompanyServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private InsuranceCompanyEntityService service;

    @Test
    public void testGetAll() throws NamingException {
	List<InsuranceCompanyEntity> all = service.getAll();
	assertThat(all,
		allOf(
			not(nullValue()),
			not(empty())));
    }

    @Test
    public void testGetById() throws NamingException {
	List<InsuranceCompanyEntity> list = service.getAll();
	for (InsuranceCompanyEntity i : list) {
	    try {
		InsuranceCompanyEntity res = service.getById(i.getId());
		assertThat(res, allOf(not(nullValue()), is(i)));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_INSURANCE_COMPANY_ID);
    }

}
