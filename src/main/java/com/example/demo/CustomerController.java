package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService service;

    @GetMapping("")
    public List<Customer> getCustomers() {
        return service.getCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable long id) {
        return Optional.of(service.getCustomerById(id));
    }

    @PostMapping()
    public void createCustomer(@RequestBody Customer newCustomer) {
        service.createCustomer(newCustomer);
    }

    @PutMapping("/{id}")
    public List<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer newCustomer) {
        return service.updateCustomer(id, newCustomer);
    }

    @DeleteMapping("/{id}")
    public List<Customer> deleteCustomer(@PathVariable long id) {
        return service.deleteCustomer(id);
    }
}
