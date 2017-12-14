package tech.lapsa.insurance.esbd;

public class NotFound extends Exception {

    private static final long serialVersionUID = 1;

    public NotFound() {
	super();
    }

    public NotFound(final String message, final Throwable cause, final boolean enableSuppression,
	    final boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotFound(final String message, final Throwable cause) {
	super(message, cause);
    }

    public NotFound(final String message) {
	super(message);
    }

    public NotFound(final Throwable cause) {
	super(cause);
    }

}
