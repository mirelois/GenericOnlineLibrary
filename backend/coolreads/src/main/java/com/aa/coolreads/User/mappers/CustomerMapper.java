package com.aa.coolreads.User.mappers;

import com.aa.coolreads.User.builder.CustomerBuilder;
import com.aa.coolreads.User.builder.UserBuilder;
import com.aa.coolreads.User.dto.BookShelfDTO;
import com.aa.coolreads.User.dto.CustomerDTO;
import com.aa.coolreads.User.dto.NewCustomerDTO;
import com.aa.coolreads.User.models.Customer;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    private final UserBuilder userBuilder;

    public CustomerMapper() {
        this.userBuilder = new CustomerBuilder();
    }

    public Customer toCustomer(NewCustomerDTO newCustomerDTO) {
        return (Customer) this.userBuilder.setUsername(newCustomerDTO.getUsername())
                .setPassword(newCustomerDTO.getPassword())
                .setEmail(newCustomerDTO.getEmail())
                .setName(newCustomerDTO.getName())
                .setGender(newCustomerDTO.getGender())
                .setPronouns(newCustomerDTO.getPronouns())
                .setBirthDate(newCustomerDTO.getBirthDate())
                .setCountry(newCustomerDTO.getCountry())
                .setDescription(newCustomerDTO.getDescription())
                .setInterests(newCustomerDTO.getInterests())
                .setProfileImageUrl(newCustomerDTO.getProfileImageUrl())
                .setProfileBannerUrl(newCustomerDTO.getProfileBannerUrl()).build();
    }

    public NewCustomerDTO toNewCustomerDTO(Customer customer) {
        return (NewCustomerDTO) this.userBuilder.setUsername(customer.getUsername())
                .setPassword(customer.getPassword())
                .setEmail(customer.getEmail())
                .setName(customer.getName())
                .setGender(customer.getGender())
                .setPronouns(customer.getPronouns())
                .setBirthDate(customer.getBirthDate())
                .setCountry(customer.getCountry())
                .setDescription(customer.getDescription())
                .setInterests(customer.getInterests())
                .setProfileImageUrl(customer.getProfileImageUrl())
                .setProfileBannerUrl(customer.getProfileBannerUrl()).build();
    }

    /*
    public CustomerDTO toCustomerDTO(Customer customer){
        CustomerDTO customerDTO = (CustomerDTO) toNewCustomerDTO(customer);

        customerDTO.setBookshelves(customer.getBookshelves().stream().map(e -> new BookShelfDTO(e.getName(), )));
    }

     */
}
