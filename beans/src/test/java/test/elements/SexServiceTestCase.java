package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.elements.Sex;
import com.lapsa.insurance.esbd.services.NotFound;
import com.lapsa.insurance.esbd.services.elements.SexServiceDAO;
import com.lapsa.insurance.esbd.services.impl.elements.mapping.SexMapping;

import test.ArquillianBaseTestCase;

public class SexServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private SexServiceDAO service;

    @Test
    public void testGetById() throws NamingException {
	for (int id : SexMapping.getInstance().getAllIds()) {
	    try {
		Sex res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(SexMapping.getInstance().forId(id))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_SEX_ID);
    }

}
