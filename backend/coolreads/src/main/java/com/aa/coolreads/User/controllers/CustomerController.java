package com.aa.coolreads.User.controllers;

import com.aa.coolreads.User.dto.*;
import com.aa.coolreads.User.exception.CustomerAlreadyExistsException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.services.AuthenticationService;
import com.aa.coolreads.User.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final AuthenticationService authenticationService;

    @Autowired
    public CustomerController(CustomerService customerService, AuthenticationService authenticationService) {
        this.customerService = customerService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO){
        try {
            this.authenticationService.signup(registerDTO);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{username}")
                    .buildAndExpand(registerDTO.getUsername())
                    .toUri();
            return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
        } catch (CustomerAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){
        try {
            return ResponseEntity.ok(this.authenticationService.authenticate(loginDTO));
        } catch(CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<SimpleCustomerDTO> getCurrentCustomerProfile(){
        try {
            return ResponseEntity.ok().body(this.customerService.getMyCustomerProfile());
        } catch (CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateCurrentCustomerProfile(@RequestBody SimpleCustomerDTO simpleCustomerDTO){
        try{
            this.customerService.updateMyCustomerProfile(simpleCustomerDTO);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<SimpleCustomerDTO> getCustomer(@PathVariable String username) {
        try{
            return ResponseEntity.ok().body(this.customerService.getCustomer(username));
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


}