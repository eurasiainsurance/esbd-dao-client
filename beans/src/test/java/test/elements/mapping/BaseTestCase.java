package test.elements.mapping;

import org.junit.BeforeClass;

import tech.lapsa.esbd.jaxws.wsimport.IICWebService;
import tech.lapsa.esbd.jaxws.wsimport.IICWebServiceSoap;
import tech.lapsa.esbd.jaxws.wsimport.User;

public abstract class BaseTestCase {

    private static final String ESBD_WS_USER_NAME = System.getenv("ASB_USER");
    private static final String ESBD_WS_USER_PASSWORD = System.getenv("ASB_PASSWORD");

    private static IICWebServiceSoap soap;
    private static String sessionId;

    @BeforeClass
    public static void createSession() {
	final IICWebService service = new IICWebService();
	soap = service.getIICWebServiceSoap();
	final User user = soap.authenticateUser(ESBD_WS_USER_NAME, ESBD_WS_USER_PASSWORD);
	sessionId = user.getSessionID();
    }

    protected static String getSessionId() {
	return sessionId;
    }

    protected static IICWebServiceSoap getSoap() {
	return soap;
    }

}
