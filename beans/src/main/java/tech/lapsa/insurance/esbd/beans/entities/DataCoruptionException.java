package com.lapsa.insurance.esbd.services.impl.entities;

public class DataCoruptionException extends RuntimeException {

    private static final long serialVersionUID = -1449421927808662699L;

    public DataCoruptionException() {
	super();
    }

    public DataCoruptionException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataCoruptionException(String message, Throwable cause) {
	super(message, cause);
    }

    public DataCoruptionException(String message) {
	super(message);
    }

    public DataCoruptionException(Throwable cause) {
	super(cause);
    }

}
