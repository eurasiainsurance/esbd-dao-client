package test.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.esbd.domain.entities.general.BranchEntity;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.general.BranchServiceDAO;

import test.ArquillianBaseTestCase;

public class BranchServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private BranchServiceDAO service;

    @Test
    public void testGetAll() throws NamingException {
	List<BranchEntity> all = service.getAll();
	assertThat(all,
		allOf(
			not(nullValue()),
			not(empty())));
    }

    @Test
    public void testGetById() throws NamingException {
	List<BranchEntity> list = service.getAll();
	for (BranchEntity i : list) {
	    try {
		BranchEntity res = service.getById(i.getId());
		assertThat(res, allOf(not(nullValue()), is(i)));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_BRANCH_ID);
    }

}
