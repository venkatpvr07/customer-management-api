package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired private CustomerRepository repo;
    // public List<Customer> customersList = new ArrayList<>();
    // Customer c1 = new Customer(1,"John Doe");
    // Customer c2 = new Customer(2,"Jane Adams");
    // Customer c3 = new Customer(3,"Alice Jones");
    //     // customersList.add(c1);
    //     // customersList.add(c2);
    //     // customersList.add(c3);
    
    // public CustomerService(){
    //     customersList.add(c1);
    //     customersList.add(c2);
    //     customersList.add(c3);
    // }

    public List<Customer> getCustomers() {
        return repo.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        // for(int i=0;i<customersList.size();i++) {
        //     if(customersList.get(i).getId() == id) {
        //         return customersList.get(i);
        //     }
        // }
        // return null;
        return repo.findById(id);
    }

    public Customer createCustomer(Customer newCustomer) {
        // customersList.add(newCustomer);
        return repo.save(newCustomer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        // for(int i=0;i<customersList.size();i++) {
        //     if(customersList.get(i).getId() == id) {
        //         // customersList.setName(customer.getName());
        //         // customersList.remove(i);
        //         Customer updatedCustomer = new Customer();
        //         updatedCustomer.setId(id);
        //         updatedCustomer.setName(customer.getName());
        //         customersList.remove(i);
        //         customersList.add(i,updatedCustomer);
        //         break;
        //     }
        // }
        Optional<Customer> currentCustomer = repo.findById(id);
        if(currentCustomer.isPresent()) {
            Customer updatedCustomer = currentCustomer.get();
            updatedCustomer.setName(customer.getName());
            return repo.save(updatedCustomer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        // for(int i=0;i<customersList.size();i++) {
        //     if(customersList.get(i).getId() == id) {
        //         customersList.remove(i);
        //         break;
        //     }
        // }
        repo.deleteById(id);
    }
}
