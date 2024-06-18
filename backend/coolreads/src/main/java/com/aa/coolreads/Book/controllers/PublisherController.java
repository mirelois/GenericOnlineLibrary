package com.aa.coolreads.Book.controllers;

import com.aa.coolreads.Book.dto.PublisherDTO;
import com.aa.coolreads.Book.exception.PublisherAlreadyExistsException;
import com.aa.coolreads.Book.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public void insertPublisher(@RequestBody PublisherDTO publisherDTO){
        try{
            this.publisherService.insertPublisher(publisherDTO);
        } catch (PublisherAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
