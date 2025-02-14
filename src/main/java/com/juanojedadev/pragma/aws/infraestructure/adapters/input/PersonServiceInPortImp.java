package com.juanojedadev.pragma.aws.infraestructure.adapters.input;

import com.juanojedadev.pragma.aws.application.ports.input.IPersonServiceInPort;
import com.juanojedadev.pragma.aws.application.ports.output.IDBRepositoryOutPort;
import com.juanojedadev.pragma.aws.domain.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service implementation to manage the person requests
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class PersonServiceInPortImp implements IPersonServiceInPort {

    /*
     * The repository extensiòn implementation for CRUD DB
     */
    private final IDBRepositoryOutPort personRepository;

    /**
     * Class constructor
     * @param personRepository the person repository to perform DB operations
     */
    public PersonServiceInPortImp(IDBRepositoryOutPort personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Person> signupPerson(Person person) {
        log.info("Signup person {}", person);
        return personRepository.save(person);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }
}
