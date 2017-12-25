package tech.lapsa.esbd.dao.beans.entities;

public class DataCoruptionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataCoruptionException() {
	super();
    }

    public DataCoruptionException(final String message, final Throwable cause) {
	super(message, cause);
    }

    public DataCoruptionException(final String message) {
	super(message);
    }

    public DataCoruptionException(final Throwable cause) {
	super(cause);
    }

}
