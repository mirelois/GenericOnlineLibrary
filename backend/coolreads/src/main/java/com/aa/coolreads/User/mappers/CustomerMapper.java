package com.aa.coolreads.User.mappers;

import com.aa.coolreads.User.builder.CustomerBuilder;
import com.aa.coolreads.User.builder.SimpleDTOBuilder;
import com.aa.coolreads.User.builder.UserBuilder;
import com.aa.coolreads.User.dto.*;
import com.aa.coolreads.User.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class CustomerMapper {

    private final UserBuilder userBuilder;

    private final UserBuilder userDTOBuilder;

    private final BookshelfMapper bookshelfMapper;

    @Autowired
    public CustomerMapper(CustomerBuilder customerBuilder, SimpleDTOBuilder simpleDTOBuilder, BookshelfMapper bookshelfMapper) {
        this.userBuilder = customerBuilder;
        this.userDTOBuilder = simpleDTOBuilder;
        this.bookshelfMapper = bookshelfMapper;
    }

    public SimpleCustomerDTO toSimpleCustomerDTO(Customer customer) {
        CustomerProfileDetails profileDetails = customer.getProfileDetails();
        return (SimpleCustomerDTO) this.userDTOBuilder.setUsername(customer.getUsername())
                .setPassword(customer.getPassword())
                .setEmail(customer.getEmail())
                .setName(profileDetails.getName())
                .setGender(profileDetails.getGender().name())
                .setPronouns(profileDetails.getPronouns())
                .setBirthDate(profileDetails.getBirthDate())
                .setCountry(profileDetails.getCountry())
                .setDescription(profileDetails.getDescription())
                .setInterests(profileDetails.getInterests())
                .setProfileImageUrl(profileDetails.getProfileImageUrl())
                .setProfileBannerUrl(profileDetails.getProfileBannerUrl()).build();
    }

    public SimpleCustomerDTO toSimpleCustomerDTO(Customer customer, Bookshelf bookshelf){
        SimpleCustomerDTO simpleCustomerDTO = this.toSimpleCustomerDTO(customer);

        simpleCustomerDTO.setHighlightedBookshelf(this.bookshelfMapper.toBookShelfDTO(bookshelf));

        return simpleCustomerDTO;
    }

    public Customer toCustomer(RegisterDTO registerDTO){
        return (Customer) this.userBuilder.setUsername(registerDTO.getUsername())
                .setPassword(registerDTO.getPassword())
                .setEmail(registerDTO.getEmail()).build();
    }

    public Author toAuthor(RegisterDTO registerDTO){
        Author author = new Author();
        author.setUsername(registerDTO.getUsername());
        author.setPassword(registerDTO.getPassword());
        author.setEmail(registerDTO.getEmail());
        return author;
    }

    public void updateProfileDetails(CustomerProfileDetails profileDetails, SimpleCustomerDTO simpleCustomerDTO) throws IllegalArgumentException {
        Optional.ofNullable(simpleCustomerDTO.getBirthDate()).ifPresent(profileDetails::setBirthDate);
        Optional.ofNullable(simpleCustomerDTO.getCountry()).ifPresent(profileDetails::setCountry);
        Optional.ofNullable(simpleCustomerDTO.getDescription()).ifPresent(profileDetails::setDescription);
        Optional.ofNullable(simpleCustomerDTO.getInterests()).ifPresent(profileDetails::setInterests);
        Optional.ofNullable(simpleCustomerDTO.getGender()).ifPresent(gender -> profileDetails.setGender(Gender.valueOf(simpleCustomerDTO.getGender())));
        Optional.ofNullable(simpleCustomerDTO.getPronouns()).ifPresent(profileDetails::setPronouns);
        Optional.ofNullable(simpleCustomerDTO.getName()).ifPresent(profileDetails::setName);
        Optional.ofNullable(simpleCustomerDTO.getProfileBannerUrl()).ifPresent(profileDetails::setProfileBannerUrl);
        Optional.ofNullable(simpleCustomerDTO.getProfileImageUrl()).ifPresent(profileDetails::setProfileImageUrl);
        Optional.ofNullable(simpleCustomerDTO.getHighlightedBookshelf())
                .map(BookShelfCreationDTO::getName)
                .ifPresent(profileDetails::setHighlightedBookshelfName);
    }

}
