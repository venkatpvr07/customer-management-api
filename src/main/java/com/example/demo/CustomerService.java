package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exceptions.CustomException;

@Service
public class CustomerService {
    @Autowired private CustomerRepository repo;

    public List<Customer> getCustomers() {
        return repo.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return repo.findById(id);
    }

    public Customer createCustomer(Customer newCustomer) {
        return repo.save(newCustomer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> currentCustomer = repo.findById(id);
        if(currentCustomer.isPresent()) {
            Customer updatedCustomer = currentCustomer.get();
            updatedCustomer.setName(customer.getName());
            return repo.save(updatedCustomer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    public Customer purchase(Long id, double purchase_amount) {
        if(purchase_amount>0){
            Optional<Customer> customer = repo.findById(id);
            if(customer.isPresent()) {
                Customer current_customer = customer.get();
                double updated_sales = current_customer.getTotalSales() + purchase_amount;
                current_customer.setTotalSales(updated_sales);
                return repo.save(current_customer);
            }
        }
        else{
            throw new CustomException("Purchase amount cannot be negative", "NEGATIVE_AMOUNT");
        }
        return null;
    }

    @Transactional
    public Customer purchaseWithCredit(Long id, double purchase_amount) {
        if(purchase_amount>0){
            Optional<Customer> customer = repo.findById(id);
            if(customer.isPresent()) {
                Customer current_customer = customer.get();
                double updated_sales = current_customer.getTotalSales() + purchase_amount;
                double updated_balance = current_customer.getBalanceDue() + purchase_amount;
                current_customer.setTotalSales(updated_sales);
                current_customer.setBalanceDue(updated_balance);
                return repo.save(current_customer);
            }
        }
        else{
        throw new CustomException("Purchase amount cannot be negative", "NEGATIVE_AMOUNT");
        }
        return null;
    } 

    @Transactional
    public Customer payment(Long id, double payment_amount) {
        if(payment_amount>0){
            Optional<Customer> customer = repo.findById(id);
            Customer current_customer = customer.get();
            if(customer.isPresent() && payment_amount <= current_customer.getBalanceDue()) {
                double updated_balance = current_customer.getBalanceDue() - payment_amount;
                current_customer.setBalanceDue(updated_balance);
                return repo.save(current_customer);
            }
            else {
                throw new CustomException("Purchase amount cannot be negative", "NEGATIVE_AMOUNT");
            }
        }
        else{
            throw new CustomException("Payment amount cannot be greater than balance due", "NEGATIVE_BALANCE");
        }
    }
}
