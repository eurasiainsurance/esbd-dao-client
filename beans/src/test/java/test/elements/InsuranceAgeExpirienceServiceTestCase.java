package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.InsuredAgeAndExpirienceClassMapping;
import tech.lapsa.insurance.esbd.elements.InsuredAgeAndExpirienceClassService;
import test.ArquillianBaseTestCase;

public class InsuranceAgeExpirienceServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private InsuredAgeAndExpirienceClassService service;

    @Test
    public void testGetById() throws NamingException {
	for (Integer id : InsuredAgeAndExpirienceClassMapping.getInstance().getAllIds()) {
	    try {
		InsuredAgeAndExpirienceClass res = service.getById(id);
		assertThat(res,
			allOf(not(nullValue()), equalTo(InsuredAgeAndExpirienceClassMapping.getInstance().forId(id))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_INSURANCE_AGE_EXPIRIENCE_ID);
    }

}
