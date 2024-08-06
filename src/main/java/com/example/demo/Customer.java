package com.example.demo;

import javax.persistence.Entity;

@Entity
public class Customer {

    @id
    @GeneratedValue(strategy=GenerationType.identity)
    private long id;
    private String name;

    public Customer(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
