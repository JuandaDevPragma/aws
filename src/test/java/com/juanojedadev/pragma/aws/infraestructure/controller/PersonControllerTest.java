package com.juanojedadev.pragma.aws.infraestructure.controller;

import com.juanojedadev.pragma.aws.application.ports.input.IPersonServiceInPort;
import com.juanojedadev.pragma.aws.domain.enums.ResponseEnums;
import com.juanojedadev.pragma.aws.domain.exception.PersistenceException;
import com.juanojedadev.pragma.aws.domain.model.Person;
import com.juanojedadev.pragma.aws.infraestructure.controller.advisor.ControllerAdvise;
import com.juanojedadev.pragma.aws.utils.TestMocks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link PersonController}
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@WebFluxTest({PersonController.class, ControllerAdvise.class})
class PersonControllerTest {

    @MockitoBean
    private IPersonServiceInPort personService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void givenPerson_whenSavePerson_thenSavePerson() {
        when(personService.signupPerson(any(Person.class)))
                .thenReturn(Mono.just(TestMocks.mockPerson()));

        webTestClient.post()
                .uri("/people/create")
                .bodyValue(TestMocks.mockPersonDto())
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .json(TestMocks.SUCCESS_RESULT);

        verify(personService).signupPerson(any(Person.class));
    }

    @Test
    void givenPerson_whenGetPerson_thenReturnPerson() {
        when(personService.getPersonById(anyLong()))
                .thenReturn(Mono.just(TestMocks.mockPerson()));

        webTestClient.get()
                .uri("/people/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(TestMocks.SUCCESS_RESULT.replace("CREATED", "OK"));

        verify(personService, times(1)).getPersonById(anyLong());
    }

    @Test
    void givenPerson_whenGetPersonButNotFound_thenAdviseResponse() {
        when(personService.getPersonById(anyLong()))
                .thenReturn(Mono.error(new PersistenceException(ResponseEnums.PERSISTENCE_INQUIRY_ERROR)));

        webTestClient.get()
                .uri("/people/1")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .json(TestMocks.FAILED_RESULT);

        verify(personService, times(1)).getPersonById(anyLong());
    }
}