package com.juanojedadev.pragma.aws.utils;

import com.juanojedadev.pragma.aws.domain.model.Person;
import com.juanojedadev.pragma.aws.infraestructure.dto.PersonDto;
import com.juanojedadev.pragma.aws.infraestructure.persistence.entity.PersonEntity;

/**
 * Test class utils for flows∫∫
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
public class TestMocks {

    public static Person mockPerson() {
        return new Person(1L, "12345", "juan", "juan@gmail.com");
    }

    public static PersonEntity mockPersonEntity() {
        return new PersonEntity(1L, "12345", "juan", "juan@gmail.com");
    }

    public static PersonDto mockPersonDto() {
        return new PersonDto(1L, "12345", "juan", "juan@gmail.com");
    }

    public static final String SUCCESS_RESULT = "{\"data\" :{\"id\" : 1, \"document\": \"12345\", \"name\":\"juan\"," +
            "\"email\":\"juan@gmail.com\"},\"status\":\"CREATED\"}";

    public static final String FAILED_RESULT = "{\"data\":\"Error searching user, please validate data\",\"status\":\"NOT_FOUND\"}";
}
