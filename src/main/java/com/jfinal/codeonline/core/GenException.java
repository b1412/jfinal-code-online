package com.jfinal.codeonline.core;

@SuppressWarnings("serial")
public class GenException extends RuntimeException {

    public GenException() {
        super();
    }

    public GenException(String message) {
        super(message);
    }

    public GenException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenException(Throwable cause) {
        super(cause);
    }
}
