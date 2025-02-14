package com.juanojedadev.pragma.aws.infraestructure.mapper;

import com.juanojedadev.pragma.aws.domain.model.Person;
import com.juanojedadev.pragma.aws.domain.wrapper.ResponseWrapper;
import com.juanojedadev.pragma.aws.infraestructure.dto.PersonDto;
import com.juanojedadev.pragma.aws.infraestructure.persistence.entity.PersonEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * The person flow mapper
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonMapperUtils {

    /**
     * Map entity to mono person Domain
     * @param entity the person entity
     * @return Mono with person domain embed
     */
    public static Mono<Person> entityToPersonDomain(PersonEntity entity) {
        return Mono.just(new Person(entity.getId(), entity.getDocumentNumber(), entity.getName(), entity.getEmail()));
    }

    /**
     * Map person dto to person domain
     * @param dto the person dto
     * @return person domain
     */
    public static Person dtoToPersonDomain(PersonDto dto) {
        return new Person(dto.getId(), dto.getDocument(), dto.getName(), dto.getEmail());
    }

    /**
     * Map person domain to dto
     * @param person the person domain
     * @return person dto
     */
    public static PersonDto personDomainToDto(Person person) {
        return new PersonDto(person.id(), person.documentNumber(), person.name(), person.email());
    }

    /**
     * Map person domain to person entity
     * @param person the person domain
     * @return person entity
     */
    public static PersonEntity personDomainToEntity(Person person) {
        PersonEntity entity = new PersonEntity();
        entity.setId(person.id());
        entity.setDocumentNumber(person.documentNumber());
        entity.setName(person.name());
        entity.setEmail(person.email());

        return entity;
    }

    /**
     * Map result flow to response entity
     * @param mono body from mono
     * @param status the status code to response
     * @return Mono with response entity result with wrapper embed
     * @param <T> generic type, may be anything
     */
    public static <T> Mono<ResponseEntity<ResponseWrapper<T>>> wrapToMonoResponse(T mono, HttpStatus status) {
        return Mono.just(ResponseEntity.status(status).body(new ResponseWrapper<T>(mono, status)));
    }

}
