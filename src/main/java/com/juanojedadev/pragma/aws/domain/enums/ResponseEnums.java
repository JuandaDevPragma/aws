package com.juanojedadev.pragma.aws.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Enum with custom message exception
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseEnums {

    PERSISTENCE_SAVING_ERROR("Error creating user, please try later", HttpStatus.INTERNAL_SERVER_ERROR ),
    PERSISTENCE_INQUIRY_ERROR("Error searching user, please validate data", HttpStatus.NOT_FOUND );

    /**
     * The error message
     */
    private String message;

    /**
     * The status message that must be replied
     */
    private HttpStatus status;
}
