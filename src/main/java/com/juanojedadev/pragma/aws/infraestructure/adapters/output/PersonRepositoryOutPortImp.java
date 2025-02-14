package com.juanojedadev.pragma.aws.infraestructure.adapters.output;

import com.juanojedadev.pragma.aws.application.ports.output.IDBRepositoryOutPort;
import com.juanojedadev.pragma.aws.domain.enums.ResponseEnums;
import com.juanojedadev.pragma.aws.domain.exception.PersistenceException;
import com.juanojedadev.pragma.aws.domain.model.Person;
import com.juanojedadev.pragma.aws.infraestructure.mapper.PersonMapperUtils;
import com.juanojedadev.pragma.aws.infraestructure.persistence.repository.IPersonDBRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Custom repository implementation for execute DB operations
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Repository
public class PersonRepositoryOutPortImp implements IDBRepositoryOutPort {

    /**
     * The repository to make persistence executions
     */
    private final IPersonDBRepository personDBRepository;

    /**
     * Class constructor
     * @param personDBRepository the person repository
     */
    public PersonRepositoryOutPortImp(IPersonDBRepository personDBRepository) {
        this.personDBRepository = personDBRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Person> save(Person person) {
        return personDBRepository
                .save(PersonMapperUtils.personDomainToEntity(person))
                .flatMap(PersonMapperUtils::entityToPersonDomain)
                .switchIfEmpty(Mono.error(new RuntimeException()))
                .onErrorMap(RuntimeException.class, e ->
                        new PersistenceException(ResponseEnums.PERSISTENCE_SAVING_ERROR));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Person> findById(Long id) {
        return personDBRepository.findById(id)
                .flatMap(PersonMapperUtils::entityToPersonDomain)
                .switchIfEmpty(Mono.error(new RuntimeException()))
                .onErrorMap(RuntimeException.class, e ->
                        new PersistenceException(ResponseEnums.PERSISTENCE_INQUIRY_ERROR));
    }
}
