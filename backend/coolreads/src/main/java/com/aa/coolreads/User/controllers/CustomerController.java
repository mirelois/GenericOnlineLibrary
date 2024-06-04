package com.aa.coolreads.User.controllers;

import com.aa.coolreads.User.dto.CustomerDTO;
import com.aa.coolreads.User.dto.NewCustomerDTO;
import com.aa.coolreads.User.exception.CustomerAlreadyExistsException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void insertCustomer(@RequestBody NewCustomerDTO newCustomerDTO) {
        try {
            this.customerService.insertCustomer(newCustomerDTO);
        } catch (CustomerAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public CustomerDTO getCustomer(@PathVariable String username) {
        try{
            return this.customerService.getCustomer(username);
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}