package com.juanojedadev.pragma.aws.application.ports.output;

import com.juanojedadev.pragma.aws.domain.Person;
import reactor.core.publisher.Mono;

public interface IPersonRepositoryOutPort {

    /**
     * Method to manages the person persistence
     * @param person information about person
     * @return Mono of person registered
     */
    public Mono<Person> save(Person person);

    /**
     * Method to return person data by ID
     * @param id the id register of person
     * @return Mono of person in database
     */
    public Mono<Person> findById(Long id);
}
