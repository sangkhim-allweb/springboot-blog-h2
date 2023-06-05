package com.allweb.springbootblogh2.exception;

import com.allweb.springbootblogh2.exception.base.ServiceException;

/**
 * trigger for data not found exception
 */
public class DataNotFoundException extends ServiceException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
