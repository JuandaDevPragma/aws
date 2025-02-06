package com.juanojedadev.pragma.aws.infraestructure.adapters.output;

import com.juanojedadev.pragma.aws.domain.exception.PersistenceException;
import com.juanojedadev.pragma.aws.domain.model.Person;
import com.juanojedadev.pragma.aws.infraestructure.persistence.entity.PersonEntity;
import com.juanojedadev.pragma.aws.infraestructure.persistence.repository.IPersonDBRepository;
import com.juanojedadev.pragma.aws.utils.TestMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link PersonRepositoryOutPortImp}
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class PersonRepositoryOutPortImpTest {

    @Mock
    private IPersonDBRepository repository;

    @InjectMocks
    private PersonRepositoryOutPortImp personRepositoryOutPortImp;

    @Test
    void givenPersonRepository_whenSave_thenSaveIt() {
        when(repository.save(any(PersonEntity.class)))
                .thenReturn(Mono.just(TestMocks.mockPersonEntity()));

        Mono<Person> result = personRepositoryOutPortImp
                .save(TestMocks.mockPerson());

        assertEquals(result.block(), TestMocks.mockPerson());
        verify(repository, times(1)).save(any(PersonEntity.class));
    }

    @Test
    void givenPersonRepository_whenSaveButErrorOccurs_thenDontSaveIt() {
        when(repository.save(any(PersonEntity.class)))
                .thenReturn(Mono.empty());

        assertThrowsExactly(PersistenceException.class, () -> personRepositoryOutPortImp
                .save(TestMocks.mockPerson()).block());

        verify(repository, times(1)).save(any(PersonEntity.class));
    }

    @Test
    void givenPersonRepositoryId_whenInquiry_thenReturnIt() {
        when(repository.findById(anyLong()))
                .thenReturn(Mono.just(TestMocks.mockPersonEntity()));

        Mono<Person> result = personRepositoryOutPortImp
                .findById(TestMocks.mockPerson().id());

        assertEquals(result.block(), TestMocks.mockPerson());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void givenPersonRepositoryId_whenInquiryButErrorOccurs_thenDontReturnIt() {
        when(repository.findById(anyLong()))
                .thenReturn(Mono.empty());

        assertThrowsExactly(PersistenceException.class, () -> personRepositoryOutPortImp
                .findById(TestMocks.mockPerson().id()).block());

        verify(repository, times(1)).findById(anyLong());
    }
}