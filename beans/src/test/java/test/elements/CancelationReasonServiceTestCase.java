package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.CancelationReasonMapping;
import tech.lapsa.insurance.esbd.elements.CancelationReasonService.CancelationReasonServiceLocal;
import test.ArquillianBaseTestCase;

public class CancelationReasonServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private CancelationReasonServiceLocal service;

    @Test
    public void testGetById() throws NamingException {
	for (int id : CancelationReasonMapping.getInstance().getAllIds()) {
	    try {
		CancelationReason res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(CancelationReasonMapping.getInstance().forId(id))));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_CANCELATION_REASON_ID);
    }

}
