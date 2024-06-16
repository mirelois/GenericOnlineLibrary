package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.LoginDTO;
import com.aa.coolreads.User.dto.LoginResponseDTO;
import com.aa.coolreads.User.dto.RegisterDTO;
import com.aa.coolreads.User.exception.CustomerAlreadyExistsException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.exception.PasswordsDontMatchException;
import com.aa.coolreads.User.mappers.BookshelfMapper;
import com.aa.coolreads.User.mappers.CustomerMapper;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.DefaultBookshelf;
import com.aa.coolreads.User.models.Privacy;
import com.aa.coolreads.User.repositories.BookshelfRepository;
import com.aa.coolreads.User.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CustomerRepository customerRepository;

    private final BookshelfRepository bookshelfRepository;

    private final BookshelfMapper bookshelfMapper;

    private final CustomerMapper customerMapper;

    private final PasswordEncoder passwordEncoder;

    private final MailService mailService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationService(CustomerRepository customerRepository, BookshelfRepository bookshelfRepository, BookshelfMapper bookshelfMapper, CustomerMapper customerMapper, MailService mailService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.bookshelfRepository = bookshelfRepository;
        this.bookshelfMapper = bookshelfMapper;
        this.customerMapper = customerMapper;
        this.mailService = mailService;
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public void signup(RegisterDTO registerDTO) throws CustomerAlreadyExistsException {

        String username = registerDTO.getUsername();
        if(this.customerRepository.findById(username).isPresent())
            throw new CustomerAlreadyExistsException(username);

        this.mailService.sendAccountCreationMail(registerDTO.getEmail());

        registerDTO.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        Customer customer = this.customerMapper.toCustomer(registerDTO);
        for(DefaultBookshelf bookshelfNameType: DefaultBookshelf.values()){
            customer.addBookshelf(this.bookshelfMapper.toBookshelf(bookshelfNameType.name(), Privacy.PRIVATE, customer));
        }
        this.customerRepository.save(customer);
    }

    @Transactional
    public LoginResponseDTO authenticate(LoginDTO loginDTO) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(loginDTO.getUsername()).orElseThrow(() -> new CustomerNotFoundException(loginDTO.getUsername()));

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        String jwtToken = this.jwtService.generateToken(customer);

        return new LoginResponseDTO(jwtToken, jwtService.getExpirationTime());
    }

    @Transactional
    public void changePassword(String oldPassword, String newPassword) throws CustomerNotFoundException, PasswordsDontMatchException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        if(!this.passwordEncoder.matches(oldPassword, customer.getPassword()))
            throw new PasswordsDontMatchException();

        customer.setPassword(this.passwordEncoder.encode(newPassword));
        this.customerRepository.save(customer);
    }
}
