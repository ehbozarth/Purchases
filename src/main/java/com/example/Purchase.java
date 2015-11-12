package com.example;

import javax.persistence.*;


/**
 * Created by earlbozarth on 11/11/15.
 */

@Entity
public class Purchase {

    @Id
    @GeneratedValue
    Integer id;

    String date;
    String credit_card;
    Integer cvv;
    String category;

    @ManyToOne
    Customer customer;
    //This is where customer_id is generated and stored

}
