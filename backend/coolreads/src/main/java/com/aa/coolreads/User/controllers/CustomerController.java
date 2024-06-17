package com.aa.coolreads.User.controllers;

import com.aa.coolreads.User.dto.*;
import com.aa.coolreads.User.exception.*;
import com.aa.coolreads.User.services.AuthenticationService;
import com.aa.coolreads.User.services.CustomerService;
import com.aa.coolreads.User.services.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final AuthenticationService authenticationService;

    private final NotificationService notificationService;

    @Autowired
    public CustomerController(CustomerService customerService, AuthenticationService authenticationService, NotificationService notificationService) {
        this.customerService = customerService;
        this.authenticationService = authenticationService;
        this.notificationService = notificationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO, @RequestParam(required = false) Boolean isAuthor){
        try {
            this.authenticationService.signup(registerDTO, isAuthor);
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
    public ResponseEntity<String> updateCurrentCustomerProfile(@RequestBody SimpleCustomerDTO simpleCustomerDTO){
        try{
            this.customerService.updateMyCustomerProfile(simpleCustomerDTO);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException | BookshelfNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Genre");
        }
    }

    @PutMapping("/me/password")
    public ResponseEntity<String> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword){
        try{
            this.authenticationService.changePassword(oldPassword, newPassword);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PasswordsDontMatchException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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

    @GetMapping("/me/friends")
    public ResponseEntity<?> getFriendsList(){
        try{
            Set<FriendDTO> friendDTOS = this.customerService.getFriendList();
            return ResponseEntity.ok().body(friendDTOS);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/me/friends")
    public ResponseEntity<String> sendFriendRequest(@RequestParam String friendUsername){
        try{
            this.notificationService.sendFriendRequestNotification(friendUsername);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/me/friends")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam String friendUsername){
        try{
            this.notificationService.addFriend(friendUsername);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NoFriendRequestFromRequestedCustomerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/me/friends")
    public ResponseEntity<String> removeFriend(@RequestParam String friendUsername){
        try{
            this.customerService.removeFriend(friendUsername);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}