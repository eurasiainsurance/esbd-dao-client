package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.insurance.elements.InsuredAgeAndExpirienceClass;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.InsuredAgeAndExpirienceClassMapping;
import tech.lapsa.insurance.esbd.elements.InsuredAgeAndExpirienceClassService.InsuredAgeAndExpirienceClassServiceLocal;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class InsuranceAgeExpirienceServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private InsuredAgeAndExpirienceClassServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final Integer id : InsuredAgeAndExpirienceClassMapping.getInstance().getAllIds())
	    try {
		final InsuredAgeAndExpirienceClass res = service.getById(id);
		assertThat(res,
			allOf(not(nullValue()), equalTo(InsuredAgeAndExpirienceClassMapping.getInstance().forId(id))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_INSURANCE_AGE_EXPIRIENCE_ID);
    }

}
