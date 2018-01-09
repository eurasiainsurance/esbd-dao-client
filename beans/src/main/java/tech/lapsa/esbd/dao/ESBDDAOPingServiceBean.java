package tech.lapsa.esbd.dao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.dao.PingService.PingServiceLocal;
import tech.lapsa.esbd.dao.PingService.PingServiceRemote;
import tech.lapsa.java.commons.exceptions.IllegalState;
import tech.lapsa.java.commons.function.MyExceptions;

@Stateless(name = PingService.BEAN_NAME)
public class PingServiceBean implements PingServiceLocal, PingServiceRemote {

    // PRIVATE

    @EJB
    private ConnectionPool pool;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void ping() throws IllegalState {
	try (Connection con = pool.getConnection()) {
	} catch (ConnectionException ce) {
	    throw MyExceptions.illegalStateFormat(ce.getMessage());
	}

    }

}
