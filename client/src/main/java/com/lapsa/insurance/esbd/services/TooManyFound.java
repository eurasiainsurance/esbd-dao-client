package com.lapsa.insurance.esbd.services;

public class TooManyFound extends Exception {

    private static final long serialVersionUID = -7567890573656824988L;

    public TooManyFound() {
	super();
    }

    public TooManyFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public TooManyFound(String message, Throwable cause) {
	super(message, cause);
    }

    public TooManyFound(String message) {
	super(message);
    }

    public TooManyFound(Throwable cause) {
	super(cause);
    }

}
