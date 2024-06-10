package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.CustomerDTO;
import com.aa.coolreads.User.dto.NewCustomerDTO;
import com.aa.coolreads.User.exception.CustomerAlreadyExistsException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.mappers.CustomerMapper;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    @Deprecated
    public void insertCustomer(NewCustomerDTO newCustomerDTO) throws CustomerAlreadyExistsException {
        String username = newCustomerDTO.getUsername();
        if(this.customerRepository.findById(username).isPresent())
            throw new CustomerAlreadyExistsException(username);

        this.customerRepository.save(this.customerMapper.toCustomer(newCustomerDTO));
    }

    @Transactional
    public CustomerDTO getCustomer(String username) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        return this.customerMapper.toCustomerDTO(customer);
    }

    public CustomerDTO getMyCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return this.customerMapper.toCustomerDTO((Customer) authentication.getPrincipal());
    }
}
