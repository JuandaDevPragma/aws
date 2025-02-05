package com.juanojedadev.pragma.aws.application.ports.input;

import com.juanojedadev.pragma.aws.domain.Person;
import reactor.core.publisher.Mono;

/**
 * Input port to specify business logic
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
public interface IPersonServiceInPort {

    /**
     * Method to manages the person persistence
     * @param person information about person
     * @return Mono of person registered
     */
    public Mono<Person> signupPerson(Person person);

    /**
     * Method to return person data by ID
     * @param id the id register of person
     * @return Mono of person in database
     */
    public Mono<Person> getPersonById(Long id);
}
