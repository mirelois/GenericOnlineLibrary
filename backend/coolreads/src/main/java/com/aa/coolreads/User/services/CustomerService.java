package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.FriendDTO;
import com.aa.coolreads.User.dto.SimpleCustomerDTO;
import com.aa.coolreads.User.exception.BookshelfNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.mappers.CustomerMapper;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.Privacy;
import com.aa.coolreads.User.repositories.BookshelfRepository;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.repositories.PersonalBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final BookshelfRepository bookshelfRepository;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookshelfRepository bookshelfRepository, PersonalBooksRepository personalBooksRepository, CustomerMapper customerMapper) {
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
    public void updateMyCustomerProfile(SimpleCustomerDTO simpleCustomerDTO, String username) throws CustomerNotFoundException, IllegalArgumentException, BookshelfNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        if(simpleCustomerDTO.getHighlightedBookshelf()!=null || simpleCustomerDTO.getHighlightedBookshelf().getName()!=null){
            this.bookshelfRepository.findBookshelfByNameAndCustomer(simpleCustomerDTO.getHighlightedBookshelf().getName(), customer)
                    .orElseThrow(() -> new BookshelfNotFoundException(simpleCustomerDTO.getHighlightedBookshelf().getName()));
        }

        this.customerMapper.updateProfileDetails(customer.getProfileDetails(), simpleCustomerDTO);

        this.customerRepository.save(customer);
    }

    @Transactional
    public void removeFriend(String my_username, String friend_username) throws CustomerNotFoundException {

        Customer myCustomer = this.customerRepository.findById(my_username).orElseThrow(() -> new CustomerNotFoundException(my_username));
        Customer customer = this.customerRepository.findById(friend_username).orElseThrow(() -> new CustomerNotFoundException(friend_username));

        customer.removeFriend(myCustomer);

        this.customerRepository.save(customer);
    }

    @Transactional
    public Set<FriendDTO> getFriendList() throws CustomerNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer myCustomer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        return myCustomer.getFriends().stream()
                .map(friend -> this.customerMapper.toFriendDTO(friend, friend.getFriends().size()))
                .collect(Collectors.toSet());
    }
}
