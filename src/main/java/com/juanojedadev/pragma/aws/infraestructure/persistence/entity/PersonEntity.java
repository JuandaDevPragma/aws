package com.juanojedadev.pragma.aws.infraestructure.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The person entity
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@Table(name = "person", schema = "person")
public class PersonEntity {

    /**
     * The person id in db
     */
    @Id
    private Long id;

    /**
     * The person document number
     */
    @Column("document_number")
    private String documentNumber;

    /**
     * The person name
     */
    private String name;

    /**
     * The person email
     */
    private String email;
}
