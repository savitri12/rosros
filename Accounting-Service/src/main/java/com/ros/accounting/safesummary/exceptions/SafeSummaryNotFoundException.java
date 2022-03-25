package com.ros.accounting.safesummary.exceptions;

public class SafeSummaryNotFoundException extends Exception{

    public SafeSummaryNotFoundException() {
        super();
    }

    public SafeSummaryNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SafeSummaryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SafeSummaryNotFoundException(String message) {
        super(message);
    }

    public SafeSummaryNotFoundException(Throwable cause) {
        super(cause);
    }

}

