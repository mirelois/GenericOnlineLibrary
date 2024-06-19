package com.aa.coolreads.configs;

import com.aa.coolreads.User.models.Author;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(username);
        if (optionalCustomer.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Customer customer = optionalCustomer.get();

        return new User(customer.getUsername(), customer.getPassword(), getAuthorities(customer));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Customer customer) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
        if (customer instanceof Author) {
            authorities.add(new SimpleGrantedAuthority("AUTHOR"));
        }
        return authorities;
    }
}
