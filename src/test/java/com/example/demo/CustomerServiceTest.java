package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    
    @Mock
    private CustomerRepository repo;

    @InjectMocks
    private CustomerService service;

    private List<Customer> customers;

    @BeforeEach
    public void setup(){
        customers = new ArrayList<>();
        Customer c1 = new Customer(1L, "Joe");
        Customer c2 = new Customer(2L, "Jill");
        Customer c3 = new Customer(3L, "Jack");
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
    }

    @Test
    public void testGetAllCustomers() {
        when(repo.findAll()).thenReturn(customers);
        List<Customer> cust = service.getCustomers();
        assertNotNull(cust);
        assertEquals(customers, cust);
        assertEquals(customers.get(0).getName(), cust.get(0).getName());
    }

    @Test
    public void testGetCustomerById(){
        when(repo.findById(1L)).thenReturn(Optional.of(new Customer(1L, "Joe")));
        Customer cust = service.getCustomerById(1L).get();
        assertNotNull(cust);
        assertEquals(1L, cust.getId());
        verify(repo).findById(cust.getId());
    }

    @Test
    public void testCreateCustomer() {
        Customer c = new Customer(5L, "Venkat");
        when(repo.save(c)).thenReturn(c);
        Customer cust = service.createCustomer(c);
        assertNotNull(cust);
        assertNotNull(cust.getName());
        assertEquals(c.getName(), cust.getName());
        verify(repo).save(cust);
    }

    @Test
    public void testUpdateCustomer() {
        when(repo.findById(1L)).thenReturn(Optional.of(customers.get(0)));
        Customer cust = customers.get(0);
        cust.setName("Venkat");
        when(repo.save(cust)).thenReturn(cust);
        Customer cust2 = service.updateCustomer(1L, cust);
        assertNotNull(cust2);
        assertEquals("Venkat", cust.getName());
        verify(repo).save(cust2);
        
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(repo).deleteById(1L);
        service.deleteCustomer(1L);
        Optional<Customer> c = repo.findById(1L);
        assertEquals(Optional.empty(), c);
        verify(repo).deleteById(1L);
    }
}
