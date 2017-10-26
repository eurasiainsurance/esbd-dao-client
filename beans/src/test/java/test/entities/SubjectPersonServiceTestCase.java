package test.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntityService;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import test.ArquillianBaseTestCase;

public class SubjectPersonServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private SubjectPersonEntityService service;

    @Test
    public void testGetById() throws NamingException {
	try {
	    for (int valid : VALID_SUBJECT_PERSON_IDS) {
		SubjectPersonEntity res = service.getById(valid);
		assertThat(res, not(nullValue()));
	    }
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    @Test
    public void testGetById_NotFound() throws NamingException {
	for (int invalid : INVALID_SUBJECT_PERSON_IDS) {
	    try {
		service.getById(invalid);
		fail("Not found exception Expected");
	    } catch (NotFound e) {
	    }
	}
    }

    public static final TaxpayerNumber[] VALID_SUBJECT_PERSON_IINS = new TaxpayerNumber[] {
	    TaxpayerNumber.of("581114350286"), TaxpayerNumber.of("870622300359") };

    @Test
    public void testGetByIIN() throws NamingException {
	try {
	    for (TaxpayerNumber valid : VALID_SUBJECT_PERSON_IINS) {
		SubjectPersonEntity res = service.getByIIN(valid);
		assertThat(res, not(nullValue()));
	    }
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    public static final TaxpayerNumber[] INVALID_SUBJECT_PERSON_IINS = new TaxpayerNumber[] {
	    TaxpayerNumber.of("930840000071") };

    @Test
    public void testGetByIIN_NotFound() throws NamingException {
	for (TaxpayerNumber invalid : INVALID_SUBJECT_PERSON_IINS) {
	    try {
		service.getByIIN(invalid);
		fail("Not found exception Expected");
	    } catch (NotFound e) {
	    }
	}
    }

}
