package test.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntity;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService;
import test.ArquillianBaseTestCase;

public class SubjectCompanyServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private SubjectCompanyEntityService service;

    @Test
    public void testGetById() throws NamingException {
	try {
	    for (int valid : VALID_SUBJECT_COMPANY_IDS) {
		SubjectCompanyEntity res = service.getById(valid);
		assertThat(res, not(nullValue()));
	    }
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    @Test
    public void testGetById_NotFound() throws NamingException {
	for (int invalid : INVALID_SUBJECT_COMPANY_IDS) {
	    try {
		service.getById(invalid);
		fail("Not found exception Expected");
	    } catch (NotFound e) {
	    }
	}
    }

    @Test
    public void testGetByBIN() throws NamingException {
	try {
	    for (String valid : VALID_SUBJECT_COMPANY_BINS) {
		SubjectCompanyEntity res = service.getByBIN(valid);
		assertThat(res, not(nullValue()));
	    }
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    @Test
    public void testGetByBIN_NotFound() throws NamingException {
	for (String invalid : INVALID_SUBJECT_COMPANY_BINS) {
	    try {
		service.getByBIN(invalid);
		fail("Not found exception Expected");
	    } catch (NotFound e) {
	    }
	}
    }
}
