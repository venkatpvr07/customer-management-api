package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public List<Customer> customersList = new ArrayList<>();
    Customer c1 = new Customer(1,"John Doe");
    Customer c2 = new Customer(2,"Jane Adams");
    Customer c3 = new Customer(3,"Alice Jones");
        // customersList.add(c1);
        // customersList.add(c2);
        // customersList.add(c3);
    
    public CustomerService(){
        customersList.add(c1);
        customersList.add(c2);
        customersList.add(c3);
    }

    public List<Customer> getCustomers() {
        return this.customersList;
    }

    public Customer getCustomerById(long id) {
        for(int i=0;i<customersList.size();i++) {
            if(customersList.get(i).getId() == id) {
                return customersList.get(i);
            }
        }
        return null;
    }

    public void createCustomer(Customer newCustomer) {
        customersList.add(newCustomer);
    }

    public List<Customer> updateCustomer(long id, Customer customer) {
        for(int i=0;i<customersList.size();i++) {
            if(customersList.get(i).getId() == id) {
                // customersList.setName(customer.getName());
                // customersList.remove(i);
                Customer updatedCustomer = new Customer();
                updatedCustomer.setId(id);
                updatedCustomer.setName(customer.getName());
                customersList.remove(i);
                customersList.add(i,updatedCustomer);
                break;
            }
        }
        return customersList;
    }

    public List<Customer> deleteCustomer(long id) {
        for(int i=0;i<customersList.size();i++) {
            if(customersList.get(i).getId() == id) {
                customersList.remove(i);
                break;
            }
        }
        return customersList;
    }
}
