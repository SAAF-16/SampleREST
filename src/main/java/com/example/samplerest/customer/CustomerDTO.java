package com.example.samplerest.customer;


import java.math.BigDecimal;

public class CustomerDTO {

    private BigDecimal id;
    private String name;
    private String email;


    public CustomerDTO() {
    }

    public CustomerDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
