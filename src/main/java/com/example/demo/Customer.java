package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// import javax.persistence.Entity;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double total_sales = 0.0;
    private double balance_due = 0.0; 

    public Customer() {}

    public Customer(String name){
        this.name = name;
    }

    public Customer(Long id, String name, Double sales, Double balance){
        this.id = id;
        this.name = name;
        this.balance_due = balance;
        this.total_sales = sales;
    }

    public Long getId(){
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

    public double getTotalSales(){
        return this.total_sales;
    }

    public void setTotalSales(double total_sales) {
        this.total_sales = total_sales;
    }

    public double getBalanceDue(){
        return this.balance_due;
    }

    public void setBalanceDue(double balance_due) {
        this.balance_due = balance_due;
    }
}
