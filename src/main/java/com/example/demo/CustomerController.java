package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
public class CustomerController {
    
    @Autowired
    private CustomerService service;

    @GetMapping("")
    public List<Customer> getCustomers() {
        return service.getCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @PostMapping()
    public Customer createCustomer(@RequestBody Customer newCustomer) {
        return service.createCustomer(newCustomer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomer) {
        return service.updateCustomer(id, newCustomer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
    }

    @PostMapping("/{id}/purchase")
    public Customer purchase(@PathVariable Long id, @RequestBody double purchase_amount) {
        return service.purchase(id, purchase_amount);
    }

    @PostMapping("/{id}/purchase-credit")
    public Customer purchaseWithCredit(@PathVariable Long id, @RequestBody double purchase_amount) {
        return service.purchaseWithCredit(id, purchase_amount);
    }

    @PostMapping("/{id}/payment")
    public Customer payBalance(@PathVariable Long id, @RequestBody double payment_amount) {
        return service.payment(id, payment_amount);
    }
}
