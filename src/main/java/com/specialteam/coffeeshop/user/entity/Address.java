package com.specialteam.coffeeshop.user.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("address")
@Data
public class Address {
    private String notes;
    private String street;
    private String postal_code;
    private String city;
    private String state;
}
