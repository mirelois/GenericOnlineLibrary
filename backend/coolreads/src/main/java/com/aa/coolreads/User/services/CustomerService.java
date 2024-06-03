package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.NewCustomerDTO;
import com.aa.coolreads.User.exception.CustomerAlreadyExistsException;
import com.aa.coolreads.User.mappers.NewCustomerMapper;
import com.aa.coolreads.User.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final NewCustomerMapper newCustomerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, NewCustomerMapper newCustomerMapper) {
        this.customerRepository = customerRepository;
        this.newCustomerMapper = newCustomerMapper;
    }

    public void insertCustomer(NewCustomerDTO newCustomerDTO) throws CustomerAlreadyExistsException {
        String username = newCustomerDTO.getUsername();
        if(this.customerRepository.findById(username).isPresent())
            throw new CustomerAlreadyExistsException(username);

        this.customerRepository.save(this.newCustomerMapper.toCustomer(newCustomerDTO));
    }
}
