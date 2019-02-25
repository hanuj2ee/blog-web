package com.blog.exception;

/**
 * BlogWebServiceException
 */
public class BlogWebServiceException extends Exception {

    public BlogWebServiceException(String message) {
        super(message);
    }

    public BlogWebServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
