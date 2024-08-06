package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// import javax.persistence.Entity;

// @Entity
public class Customer {

    // @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;

    public Customer() {}

    public Customer(String name){
        this.name = name;
    }

    public Customer(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
