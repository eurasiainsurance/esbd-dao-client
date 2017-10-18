package test.elements;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static test.entities.TestConstants.*;

import java.util.Set;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Test;

import com.lapsa.insurance.elements.InsuranceClassType;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.beans.elements.mapping.InsuranceClassTypeMapping;
import tech.lapsa.insurance.esbd.elements.InsuranceClassTypeService;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;
import test.ArquillianBaseTestCase;

public class InsuranceClassTypeTestCase extends ArquillianBaseTestCase {

    @Inject
    private InsuranceClassTypeService service;

    @Test
    public void testGetById() throws NamingException {
	try {
	    InsuranceClassType res = service.getById(VALID_INSURANCE_CLASS_TYPE_ID);
	    assertThat(res, allOf(not(nullValue()), equalTo(InsuranceClassType.CLASS_12)));
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NamingException, NotFound {
	service.getById(INVALID_INSURANCE_CLASS_TYPE_ID);
    }

    @Test
    public void testGetByCode() throws NamingException {
	InsuranceClassType[] list = InsuranceClassType.values();
	Set<String> codes = InsuranceClassTypeMapping.getInstance().getAllIds();
	for (String id : codes) {
	    try {
		InsuranceClassType res = service.getByCode(id);
		assertThat(res, allOf(not(nullValue()), isIn(list)));
	    } catch (NotFound e) {
		fail(e.getMessage());
	    }
	}
    }

    @Test(expected = NotFound.class)
    public void testGetByCode_NotFound() throws NamingException, NotFound {
	service.getByCode(INVALID_INSURANCE_CLASS_TYPE_CODE);
    }

    @Test
    public void testGetForSubject() throws NamingException {
	try {
	    SubjectPersonEntity e = new SubjectPersonEntity();
	    e.setId(VALID_SUBJECT_PERSON_ID);
	    InsuranceClassType res = service.getForSubject(e);
	    assertThat(res, allOf(not(nullValue()), equalTo(VALID_CLASS_TYPE_FOR_CLIENT)));
	} catch (NotFound e) {
	    fail(e.getMessage());
	}
    }

    @Test(expected = NotFound.class)
    public void testGetForSubject_NotFound() throws NamingException, NotFound {
	SubjectPersonEntity e = new SubjectPersonEntity();
	e.setId(INVALID_SUBJECT_PERSON_ID);
	service.getForSubject(e);
    }

}
