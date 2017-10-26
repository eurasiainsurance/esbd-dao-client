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
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
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

    public static final TaxpayerNumber[] VALID_SUBJECT_COMPANY_BINS = new TaxpayerNumber[] {
	    TaxpayerNumber.of("930840000071") };

    @Test
    public void testGetByBIN() throws NamingException {
	try {
	    for (TaxpayerNumber valid : VALID_SUBJECT_COMPANY_BINS) {
		SubjectCompanyEntity res = service.getByBIN(valid);
		assertThat(res, not(nullValue()));
	    }
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    public static final TaxpayerNumber[] INVALID_SUBJECT_COMPANY_BINS = new TaxpayerNumber[] {
	    TaxpayerNumber.of("581114350286"), TaxpayerNumber.of("ZZZZ") };

    @Test
    public void testGetByBIN_NotFound() throws NamingException {
	for (TaxpayerNumber invalid : INVALID_SUBJECT_COMPANY_BINS) {
	    try {
		service.getByBIN(invalid);
		fail("Not found exception Expected");
	    } catch (NotFound e) {
	    }
	}
    }
}
