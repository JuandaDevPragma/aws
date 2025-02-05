package com.juanojedadev.pragma.aws.infraestructure.controller.advisor;

import com.juanojedadev.pragma.aws.domain.exception.PersistenceException;
import com.juanojedadev.pragma.aws.domain.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * The controller advisor to recover and replicate exceptions in response
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class ControllerAdvise {

    /**
     * Exception handler for persistence exceptions
     * @param e the error class
     * @return new response body with result performed by error
     */
    @ExceptionHandler(PersistenceException.class)
    public Mono<ResponseEntity<ResponseWrapper<String>>> handlePersistenceException(PersistenceException e) {
        return Mono.just(ResponseEntity.status(e.getReason().getStatus())
                .body(new ResponseWrapper<>(e.getReason().getMessage(), e.getReason().getStatus())));
    }

}
