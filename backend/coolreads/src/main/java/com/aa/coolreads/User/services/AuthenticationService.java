package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.LoginDTO;
import com.aa.coolreads.User.dto.LoginResponseDTO;
import com.aa.coolreads.User.dto.RegisterDTO;
import com.aa.coolreads.User.exception.CustomerAlreadyExistsException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.mappers.CustomerMapper;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationService(CustomerRepository customerRepository, CustomerMapper customerMapper, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.customerMapper = customerMapper;
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

        registerDTO.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        this.customerRepository.save(this.customerMapper.toCustomer(registerDTO));
    }

    @Transactional
    public LoginResponseDTO authenticate(LoginDTO loginDTO) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(loginDTO.getUsername()).orElseThrow(() -> new CustomerNotFoundException(loginDTO.getUsername()));

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        String jwtToken = this.jwtService.generateToken(customer);

        return new LoginResponseDTO(jwtToken, jwtService.getExpirationTime());
    }
}
