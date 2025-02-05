package com.juanojedadev.pragma.aws.infraestructure.adapters.input;

import com.juanojedadev.pragma.aws.application.ports.input.IPersonServiceInPort;
import com.juanojedadev.pragma.aws.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service to manage the respective person creation
 */
@Slf4j
@Service
public class PersonServiceInPortImp implements IPersonServiceInPort {

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Person> signupPerson(Person person) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Person> getPersonById(Long id) {
        return null;
    }
}
