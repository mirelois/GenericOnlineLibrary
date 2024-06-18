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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/customer")
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
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try {
            return ResponseEntity.ok(this.authenticationService.authenticate(loginDTO));
        } catch(CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentCustomerProfile(){
        try {
            SimpleCustomerDTO simpleCustomerDTO = this.customerService.getMyCustomerProfile();
            return ResponseEntity.ok().body(simpleCustomerDTO);
        } catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateCurrentCustomerProfile(@RequestBody SimpleCustomerDTO simpleCustomerDTO){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        try{
            this.customerService.updateMyCustomerProfile(simpleCustomerDTO, username);
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

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getCustomer(@PathVariable String username) {
        try{
            return ResponseEntity.ok().body(this.customerService.getCustomer(username));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            this.notificationService.sendFriendRequestNotification(username, friendUsername);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/me/friends")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam String friendUsername){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            this.notificationService.addFriend(username, friendUsername);
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
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            this.customerService.removeFriend(username, friendUsername);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/me/notifications")
    public ResponseEntity<?> getNotifications(@RequestParam Integer page, @RequestParam Integer size){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            Set<NotificationDTO> notificationDTOS = this.notificationService.getNotificationsByUserName(username, page, size);
            return ResponseEntity.ok().body(notificationDTOS);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/me/notifications")
    public ResponseEntity<String> removeNotification(@RequestParam Long notificationId){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            this.notificationService.deleteNotification(username, notificationId);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}