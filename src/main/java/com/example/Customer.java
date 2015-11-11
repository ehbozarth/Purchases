package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by earlbozarth on 11/11/15.
 */

@Entity
public class Customer {

    @Id
    @GeneratedValue
    Integer id;

    String name;
    String email;

    @OneToMany(mappedBy = "customer")
    List<Purchase> purchases;

}
