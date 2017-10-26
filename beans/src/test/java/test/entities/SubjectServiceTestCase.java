package test.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.elements.SubjectType;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.SubjectEntity;
import tech.lapsa.insurance.esbd.entities.SubjectEntityService;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import test.ArquillianBaseTestCase;

public class SubjectServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private SubjectEntityService service;

    @Test
    public void testGetById() throws NamingException {
	try {
	    for (int i = 0; i < VALID_SUBJECT_IDS.length; i++) {
		int validSubjectId = VALID_SUBJECT_IDS[i];
		SubjectType validSubjectType = VALID_SUBJECT_TYPES[i];
		Class<?> validSubjectClass = VALID_SUBJECT_CLASSES[i];
		SubjectEntity res = service.getById(validSubjectId);
		assertThat(res, allOf(not(nullValue()), instanceOf(validSubjectClass)));
		assertThat(res.getSubjectType(), allOf(not(nullValue()), is(validSubjectType)));
	    }
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_SUBJECT_ID);
    }

    public static final TaxpayerNumber[] VALID_SUBJECT_ID_NUMBERS = new TaxpayerNumber[] {
	    TaxpayerNumber.of("930840000071"),
	    TaxpayerNumber.of("581114350286") };

    @Test
    public void testGetByIDNumber() throws NamingException {
	try {
	    for (int i = 0; i < VALID_SUBJECT_ID_NUMBERS.length; i++) {
		TaxpayerNumber subjecdIdNumber = VALID_SUBJECT_ID_NUMBERS[i];
		SubjectType validSubjectType = VALID_SUBJECT_TYPES[i];
		Class<?> validSubjectClass = VALID_SUBJECT_CLASSES[i];
		SubjectEntity res = service.getByIdNumber(subjecdIdNumber);
		assertThat(res, allOf(not(nullValue()), instanceOf(validSubjectClass)));
		assertThat(res.getSubjectType(), allOf(not(nullValue()),
			is(validSubjectType)));
		assertThat(res.getIdNumber(), allOf(not(nullValue()), is(subjecdIdNumber.getNumber())));
	    }
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    public static final TaxpayerNumber INVALID_SUBJECT_ID_NUMBER = TaxpayerNumber.of("800225000001");

    @Test(expected = NotFound.class)
    public void testGetByIDNumber_NotFound() throws NamingException, NotFound {
	service.getByIdNumber(INVALID_SUBJECT_ID_NUMBER);
    }

}
