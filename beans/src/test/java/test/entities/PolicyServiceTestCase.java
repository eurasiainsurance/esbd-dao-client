package test.entities;

import static test.entities.TestConstants.*;

import javax.inject.Inject;

import org.junit.Test;

import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.PolicyEntityService.PolicyEntityServiceLocal;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import test.ArquillianBaseTestCase;

public class PolicyServiceTestCase extends ArquillianBaseTestCase {

    @Inject
    private PolicyEntityServiceLocal service;

    @Test(expected = NotFound.class)
    public void testGetById_NotFound() throws NotFound, IllegalArgument {
	service.getById(INVALID_POLICY_ID);
    }

    @Test(expected = NotFound.class)
    public void testGetByPolicyNumber_NotFound() throws NotFound, IllegalArgument {
	service.getByNumber(INVALID_POLICY_NUMBER);
    }
}
