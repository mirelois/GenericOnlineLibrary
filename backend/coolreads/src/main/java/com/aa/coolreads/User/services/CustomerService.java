package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.SimpleCustomerDTO;
import com.aa.coolreads.User.exception.BookshelfNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.mappers.CustomerMapper;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.repositories.BookshelfRepository;
import com.aa.coolreads.User.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final BookshelfRepository bookshelfRepository;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookshelfRepository bookshelfRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.bookshelfRepository = bookshelfRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public SimpleCustomerDTO getCustomer(String username) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));
        Optional<Bookshelf> highlitedBookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(customer.getProfileDetails().getHighlightedBookshelfName(), customer);
        if(highlitedBookshelf.isPresent()){
            return this.customerMapper.toSimpleCustomerDTO(customer, highlitedBookshelf.get());
        } else{
            return this.customerMapper.toSimpleCustomerDTO(customer);
        }
    }

    @Transactional
    public SimpleCustomerDTO getMyCustomerProfile() throws CustomerNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.getCustomer(authentication.getName());
    }

    @Transactional
    public void updateMyCustomerProfile(SimpleCustomerDTO simpleCustomerDTO) throws CustomerNotFoundException, IllegalArgumentException, BookshelfNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        if(simpleCustomerDTO.getHighlightedBookshelf()!=null || simpleCustomerDTO.getHighlightedBookshelf().getName()!=null){
            this.bookshelfRepository.findBookshelfByNameAndCustomer(simpleCustomerDTO.getHighlightedBookshelf().getName(), customer)
                    .orElseThrow(() -> new BookshelfNotFoundException(simpleCustomerDTO.getHighlightedBookshelf().getName()));
        }

        this.customerMapper.updateProfileDetails(customer.getProfileDetails(), simpleCustomerDTO);

        this.customerRepository.save(customer);
    }

    @Transactional
    public void removeFriend(String friend_username) throws CustomerNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer myCustomer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));
        Customer customer = this.customerRepository.findById(friend_username).orElseThrow(() -> new CustomerNotFoundException(friend_username));

        customer.removeFriend(myCustomer);

        this.customerRepository.save(customer);
    }
}
