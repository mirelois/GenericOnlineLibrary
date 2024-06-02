package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.mappers.PublisherMapper;
import com.aa.coolreads.Book.dto.PublisherDTO;
import com.aa.coolreads.Book.exception.PublisherAlreadyExistsException;
import com.aa.coolreads.Book.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;
    private PublisherMapper publisherMapper;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    public void insertPublisher(PublisherDTO publisherDTO) throws PublisherAlreadyExistsException {
        String publisherName = publisherDTO.getName();
        if(this.publisherRepository.findById(publisherName).isPresent()){
            throw new PublisherAlreadyExistsException(publisherName);
        }

        this.publisherRepository.save(this.publisherMapper.toPublisher(publisherDTO));
    }
}
