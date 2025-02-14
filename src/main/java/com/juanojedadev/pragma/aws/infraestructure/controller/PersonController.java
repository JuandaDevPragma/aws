package com.juanojedadev.pragma.aws.infraestructure.controller;

import com.juanojedadev.pragma.aws.application.ports.input.IPersonServiceInPort;
import com.juanojedadev.pragma.aws.domain.wrapper.ResponseWrapper;
import com.juanojedadev.pragma.aws.infraestructure.dto.PersonDto;
import com.juanojedadev.pragma.aws.infraestructure.mapper.PersonMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * The person controller
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/people")
public class PersonController {

    /**
     * The input port to manage core communication
     */
    private final IPersonServiceInPort personService;

    /**
     * Class constructor
     * @param personService the input port to make core processing
     */
    public PersonController(IPersonServiceInPort personService) {
        this.personService = personService;
    }

    /**
     * Endpoint to manage the person persistence in DB
     * @param personDto the request DTO
     * @return Mono with operation input result
     */
    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public Mono<ResponseEntity<ResponseWrapper<PersonDto>>> savePerson(@RequestBody PersonDto personDto) {
        return personService.signupPerson(PersonMapperUtils.dtoToPersonDomain(personDto))
                .flatMap(result -> PersonMapperUtils.wrapToMonoResponse(
                        PersonMapperUtils.personDomainToDto(result), HttpStatus.CREATED)
                );
    }

    /**
     * Endpoint to return person data from DB
     * @param id the person id to filter data
     * @return Mono with operation inquiry result
     */
    @GetMapping(value = "/{id}",produces = "application/json")
    public Mono<ResponseEntity<ResponseWrapper<PersonDto>>> getPerson(@PathVariable Long id, @AuthenticationPrincipal Jwt principal) {
            return personService.getPersonById(id)
                    .flatMap(result -> PersonMapperUtils
                        .wrapToMonoResponse(PersonMapperUtils.personDomainToDto(result), HttpStatus.OK));
    }
}
