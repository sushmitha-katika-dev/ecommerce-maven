package com.sushi.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String houseNo;
    private String building;
    private String landMark;
    private String street;
    private String city;
    private String district;
    private String state;
    private String country;
    private int zipCode;
}
