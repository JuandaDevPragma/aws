package com.juanojedadev.pragma.aws.infraestructure.persistence.repository;

import com.juanojedadev.pragma.aws.infraestructure.persistence.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The person crud repository managed by ORM
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface IPersonDBRepository extends ReactiveCrudRepository<PersonEntity, Long> {
}
