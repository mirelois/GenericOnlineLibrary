package com.aa.coolreads.Book.components;

import com.aa.coolreads.Book.dto.PublisherDTO;
import com.aa.coolreads.Book.models.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper {
    public Publisher toPublisher(PublisherDTO publisherDTO) {
        return new Publisher(publisherDTO.getName(), publisherDTO.getEmail(), publisherDTO.getSiteUrl(), publisherDTO.getLogoUrl());
    }

    public PublisherDTO publisherDTO(Publisher publisher) {
        return new PublisherDTO(publisher.getName(), publisher.getEmail(), publisher.getSiteUrl(), publisher.getLogoUrl());
    }
}
