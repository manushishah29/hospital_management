package com.softvan.hospitalManagement.exception;

import org.springframework.http.HttpStatus;

/**
 * <h1>CustomException</h1>
 * <p>
 * This class will be used for handling Custom exception
 * </p>
 *
 * @author finpal
 * @version 1.0
 * @since 20-09-2022
 */

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    /**
     * <p>
     * This Method handles CustomException.
     * </p>
     *
     * @param message    contains message value
     * @param httpStatus contains http status
     */
    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
