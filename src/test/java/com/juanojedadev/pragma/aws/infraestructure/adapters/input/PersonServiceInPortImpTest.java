package com.juanojedadev.pragma.aws.infraestructure.adapters.input;

import com.juanojedadev.pragma.aws.application.ports.output.IDBRepositoryOutPort;
import com.juanojedadev.pragma.aws.domain.model.Person;
import com.juanojedadev.pragma.aws.utils.TestMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Test class for {@link PersonServiceInPortImp}
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class PersonServiceInPortImpTest {

    @Mock
    private IDBRepositoryOutPort personDBRepository;

    @InjectMocks
    private PersonServiceInPortImp personServiceInPortImp;

    @Test
    void givenPersonForRequest_whenSavePerson_thenSavePerson() {
        when(personDBRepository.save(any(Person.class)))
                .thenReturn(Mono.just(TestMocks.mockPerson()));

        Mono<Person> result = personServiceInPortImp
                .signupPerson(TestMocks.mockPerson());

        assertEquals(result.block(), TestMocks.mockPerson());
        verify(personDBRepository, times(1)).save(any(Person.class));
    }

    @Test
    void givenPersonIdForRequest_whenInquiryPerson_thenReturnPerson() {
        when(personDBRepository.findById(anyLong()))
                .thenReturn(Mono.just(TestMocks.mockPerson()));

        Mono<Person> result = personServiceInPortImp
                .getPersonById(TestMocks.mockPerson().id());

        assertEquals(result.block(), TestMocks.mockPerson());
        verify(personDBRepository, times(1)).findById(anyLong());
    }
}