package tech.lapsa.esbd.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.java.commons.exceptions.IllegalState;

public interface PingService extends EJBConstants {

    public static final String BEAN_NAME = "PingServiceBean";

    @Local
    public interface PingServiceLocal extends PingService {
    }

    @Remote
    public interface PingServiceRemote extends PingService {
    }

    void ping() throws IllegalState;
}
