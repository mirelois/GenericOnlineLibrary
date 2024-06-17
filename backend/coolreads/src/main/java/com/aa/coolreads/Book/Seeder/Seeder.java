package com.aa.coolreads.Book.Seeder;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.dto.PublisherDTO;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.exception.PublisherAlreadyExistsException;
import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.Book.services.BookService;
import com.aa.coolreads.Book.services.PublisherService;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.RegisterDTO;
import com.aa.coolreads.User.exception.*;
import com.aa.coolreads.User.mappers.BookshelfMapper;
import com.aa.coolreads.User.mappers.CustomerMapper;
import com.aa.coolreads.User.models.*;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.services.AuthenticationService;
import com.aa.coolreads.User.services.BookshelfService;
import com.aa.coolreads.User.services.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class Seeder implements CommandLineRunner {

    CustomerService customerService;

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;
    private final BookshelfMapper bookshelfMapper;
    private final BookshelfService bookshelfService;
    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthenticationService authenticationService;

    public Seeder(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, CustomerMapper customerMapper, BookshelfMapper bookshelfMapper, BookshelfService bookshelfService, BookService bookService, PublisherService publisherService, AuthenticationService authenticationService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerMapper = customerMapper;
        this.bookshelfMapper = bookshelfMapper;
        this.bookshelfService = bookshelfService;
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authenticationService = authenticationService;
    }

    private static int hashOp(int i) {
        // used to determine C
        int doubled = 2 * i;
        if (doubled >= 10) {
            doubled = doubled - 9;
        }
        return doubled;
    }

    private static String makeISBN() {
        String laendercode;
        String bandnr;
        String verlagsnr;
        String checksum;

// Generate Random Numbers for L1L2-B1B2B3-V1V2
        double L1 = Math.random() * (10);
        double L2 = Math.random() * (10);

        double B1 = Math.random() * (10);
        double B2 = Math.random() * (10);
        double B3 = Math.random() * (10);

        double V1 = Math.random() * (10);
        double V2 = Math.random() * (10);

// Check that L1L2 > 0
        if ((int) L1 == 0 && (int) L2 == 0) {
            L2++;
        }
// Check that L1B2B3 >= 100
        if ((int) B1 == 0) {
            B1++;
        }
// Check that V1V2 > 0
        if ((int) V1 == 0 && (int) V2 == 0) {
            V2++;
        }
// Compute check digit with hashOp method
        double C = (hashOp((int) L1) + L2 + hashOp((int) B1) + B2 + hashOp((int) B3) + V1 + hashOp((int) V2)) % 10;

// Convert the generated numbers to String
        laendercode = (int) L1 + "" + (int) L2;
        bandnr = (int) B1 + "" + (int) B2 + "" + (int) B3;
        verlagsnr = (int) V1 + "" + (int) V2;
        checksum = (int) C + "";

        return laendercode + "-" + bandnr + "-" + verlagsnr + "-" + checksum;
    }

    private static String makeDate() {
        Random rand = new Random();

        rand.setSeed(System.currentTimeMillis());

        int year = rand.nextInt(1970, 2025);

        int month = rand.nextInt(12) + 1;

        int day;

        if (month != 2) {
            day = rand.nextInt(31) + 1;
        }else {
            day = rand.nextInt(28) + 1;
        }
        return year + "-" + month + "-" + day;
    }


    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadPublisher(){

        for (int i = 0; i < 4; i++) {
            PublisherDTO publisherDTO = new PublisherDTO("publisher" + i, "email"+i+"@gmail.com", "siteurl"+i, "logourl"+i);
            try {
                publisherService.insertPublisher(publisherDTO);
            } catch (PublisherAlreadyExistsException e) {
            }
        }

    }

    private void loadBookData() {

        Set<String> genre = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            genre.add("genre"+i);
        }


        for (int i = 0; i < 100; i++) {

            BookDTO bookDTO = new BookDTO(
                    makeISBN(),
                    "title"+i,
                    "description" + i,
                    makeDate(),
                    200,
                    "publisher"+(i % 4),
                    genre
                    );

            bookService.insertBook();

        }

    }

    private void loadCustomerData() {

        //inserting customers
        for (int i = 0; i < 100; i++) {

            RegisterDTO registerDTO = new RegisterDTO("user" + i, "password" + i, "email" + i + "@gmail.com");

            try {
                authenticationService.signup(registerDTO);
            } catch (CustomerAlreadyExistsException e) {
            }

            BookShelfCreationDTO bookShelfCreationDTO = new BookShelfCreationDTO("bookshelf" + i, Privacy.values()[i % Privacy.values().length].name());

            try {
                bookshelfService.insertBookshelf(bookShelfCreationDTO, "user" + i);
            } catch (CustomerNotFoundException e) {
            } catch (BookshelfAlreadyExists e) {
            }

            for (int j = 0; j < 10; j++) {

                try {
                    bookshelfService.insertBook("already_read", "user" + i, makeISBN());
                } catch (CustomerNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (BookshelfNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (BookNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (PersonalBookAlreadyExistsException e) {
                    throw new RuntimeException(e);
                }

            }

        }


    }

}
