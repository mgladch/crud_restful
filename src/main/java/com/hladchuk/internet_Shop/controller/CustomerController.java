package com.hladchuk.internet_Shop.controller;

import com.hladchuk.internet_Shop.model.Customer;
import com.hladchuk.internet_Shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:9191")
@RestController
@RequestMapping("/api")
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    ResponseEntity<List<Customer>> findAllCustomers() {
        try {
            List<Customer> customers = new ArrayList<>();
            customers.addAll(customerService.findAll());

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/customerById/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") int id) {
        Optional<Customer> customer = Optional.ofNullable(customerService.findById(id).get());
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/customerByName/{name}")
    public ResponseEntity<List<Customer>> findCustomerByName(@PathVariable("name") String name) {
        try {
            List<Customer> customerWithName = customerService.findAll().stream()
                    .filter(customer -> customer.getName().equals(name))
                    .collect(Collectors.toList());
            if (customerWithName.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerWithName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService
                    .save(customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        Optional<Customer> customerData = Optional.ofNullable(customerService.findById(id)).get();

        if (customerData.isPresent()) {
            Customer updatedCustomer = customerData.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setSurname(customer.getSurname());
            updatedCustomer.setEmail(customer.getEmail());
            return new ResponseEntity<>(customerService.save(updatedCustomer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> removeCustomer(@PathVariable("id") int id) {
        try {
            Customer customer = customerService.findById(id).get();
            customerService.removeById(customer.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
