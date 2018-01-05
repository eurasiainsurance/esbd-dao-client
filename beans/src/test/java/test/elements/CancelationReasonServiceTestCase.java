package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.elements.CancelationReasonService.CancelationReasonServiceLocal;
import tech.lapsa.esbd.dao.elements.mapping.CancelationReasonMapping;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class CancelationReasonServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private CancelationReasonServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final int id : CancelationReasonMapping.getInstance().getAllIds())
	    try {
		final CancelationReason res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(CancelationReasonMapping.getInstance().forId(id))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_CANCELATION_REASON_ID);
    }

}
