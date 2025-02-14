package com.juanojedadev.pragma.aws.domain.exception;

import com.juanojedadev.pragma.aws.domain.enums.ResponseEnums;
import lombok.Getter;

/**
 * Custom exception for persistence errors
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Getter
public class PersistenceException extends RuntimeException{

    /**
     * Enum with additional error data
     */
    private final ResponseEnums reason;

    /**
     * Class constructor
     * @param reason the error issue
     */
    public PersistenceException(ResponseEnums reason) {
        this.reason = reason;
    }
}
