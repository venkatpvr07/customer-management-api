package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
// @ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    // @Autowired
    // private MockMvc mockMvc;
    private List<Customer> customers;

    @Mock
    private CustomerService myService;

    @InjectMocks
    private CustomerController myController;

    // @BeforeEach
    // public void setUp() {
    //     MockitoAnnotations.openMocks(this);
    //     // mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
    // }

    @BeforeEach
    public void setup(){
        customers = new ArrayList<>();
        Customer c1 = new Customer(1L, "Joe", 0.0,0.0);
        Customer c2 = new Customer(2L, "Jill", 0.0, 0.0);
        Customer c3 = new Customer(3L, "Jack", 0.0, 0.0);
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
    }

    @Test
    public void testGetCustomers() throws Exception {
        List<Customer> customers2 = new ArrayList<>();
        customers2.add(new Customer(1L, "John Doe", 0.0,0.0));
        customers2.add(new Customer(2L, "Alice Jones",0.0,0.0));

        when(myService.getCustomers()).thenReturn(customers2);

        List<Customer> result = myController.getCustomers();
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Alice Jones", result.get(1).getName());

        verify(myService).getCustomers();
    }

     @Test
    public void testGetCustomerById(){
        Customer c = customers.get(0);
        when(myService.getCustomerById(1L)).thenReturn(Optional.of(c));
        Customer cust = myController.getCustomerById(1L).get();
        assertNotNull(cust);
        assertEquals(1L, cust.getId());
        verify(myService).getCustomerById(c.getId());
    }

    @Test
    public void testCreateCustomer() {
        Customer c = new Customer(5L, "Venkat", 0.0, 0.0);
        when(myService.createCustomer(c)).thenReturn(c);
        Customer cust = myController.createCustomer(c);
        assertNotNull(cust);
        assertNotNull(cust.getName());
        assertEquals(c.getName(), cust.getName());
        verify(myService).createCustomer(c);
    }

    @Test
    public void testUpdateCustomer() {
        Customer cust = customers.get(0);
        cust.setName("Venkat");
        when(myService.updateCustomer(1L, cust)).thenReturn(cust);
        Customer cust2 = myController.updateCustomer(1L, cust);
        assertNotNull(cust2);
        assertEquals("Venkat", cust2.getName());
        verify(myService).updateCustomer(1L, cust);
        
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(myService).deleteCustomer(1L);
        myController.deleteCustomer(1L);
        verify(myService).deleteCustomer(1L);
        when(myService.getCustomerById(1L)).thenReturn(null);
        assertNull(myService.getCustomerById(1L));
    }

}
