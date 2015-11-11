package com.example;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by earlbozarth on 11/11/15.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
