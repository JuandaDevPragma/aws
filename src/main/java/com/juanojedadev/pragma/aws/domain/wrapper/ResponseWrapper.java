package com.juanojedadev.pragma.aws.domain.wrapper;

import org.springframework.http.HttpStatus;

/**
 * Record wrapper to manage standard responses
 *
 * @param data the data of response, must be anything type
 * @param status the status code to answer the request
 * @param <T> any type for response wrappers
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
public record ResponseWrapper<T> (T data, HttpStatus status) {}
