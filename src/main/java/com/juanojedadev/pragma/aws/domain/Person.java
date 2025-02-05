package com.juanojedadev.pragma.aws.domain;

/**
 * Record to manage domain data of person
 *
 * @param id the person ID
 * @param documentNumber the person legal identification number
 * @param name the customer name
 * @param email the person email
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
public record Person (Long id, String documentNumber, String name, String email) {}
