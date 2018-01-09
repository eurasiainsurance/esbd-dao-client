package tech.lapsa.esbd.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import tech.lapsa.java.commons.exceptions.IllegalState;

public interface ESBDDAOPingService extends EJBConstants {

    public static final String BEAN_NAME = "ESBDDAOPingServiceBean";

    @Local
    public interface ESBDDAOPingServiceLocal extends ESBDDAOPingService {
    }

    @Remote
    public interface ESBDDAOPingServiceRemote extends ESBDDAOPingService {
    }

    void ping() throws IllegalState;
}
