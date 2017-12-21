package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import com.lapsa.insurance.elements.IdentityCardType;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.IdentityCardTypeMapping;
import tech.lapsa.insurance.esbd.elements.IdentityCardTypeService.IdentityCardTypeServiceLocal;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class IdentityCardTypeServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private IdentityCardTypeServiceLocal service;

    @Test
    public void testGetById() throws IllegalArgument {
	for (final int id : IdentityCardTypeMapping.getInstance().getAllIds())
	    try {
		final IdentityCardType res = service.getById(id);
		assertThat(res, allOf(not(nullValue()), equalTo(IdentityCardTypeMapping.getInstance().forId(id))));
	    } catch (final NotFound e) {
		fail(e.getMessage());
	    }
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_IDENTITY_CARD_TYPE_ID);
    }
}
