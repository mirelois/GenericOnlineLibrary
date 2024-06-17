package com.aa.coolreads.Seeder;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.dto.PublisherDTO;
import com.aa.coolreads.Book.exception.*;
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
import org.apache.commons.validator.routines.ISBNValidator;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.ISBNCheckDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Seeder implements CommandLineRunner {

    private final BookshelfService bookshelfService;
    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthenticationService authenticationService;

    public Seeder(BookshelfService bookshelfService, BookService bookService, PublisherService publisherService, AuthenticationService authenticationService) {
        this.bookshelfService = bookshelfService;
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authenticationService = authenticationService;
    }


    private static String makeISBN() throws CheckDigitException {

        Random random = new Random();
        random.setSeed(2024);

        StringBuilder prefix = new StringBuilder("978");

        for (int i = 0; i < 9; i++) {
            prefix.append(random.nextInt(10));
        }

        ISBNCheckDigit isbnCheckDigit = new ISBNCheckDigit();

        System.out.println(prefix);

        String digit = isbnCheckDigit.calculate(prefix.toString());

        return prefix + digit;

    }

    private static Date makeDate() {
        Random rand = new Random();
        rand.setSeed(2024);

        rand.setSeed(System.currentTimeMillis());

        int year = rand.nextInt(1970, 2025);

        int month = rand.nextInt(12) + 1;

        int day;

        if (month != 2) {
            day = rand.nextInt(31) + 1;
        }else {
            day = rand.nextInt(28) + 1;
        }
        return new Date(year, month, day);
    }


    @Override
    public void run(String... args) throws Exception {

        ArrayList<String> isbns;

        loadPublisher();
        loadAuthor();
        isbns = loadBookData();
        loadCustomerData();
        loadBookshelfData();
        loadPersonalBookData(isbns);
    }

    private void loadPersonalBookData(ArrayList<String> isbns) {

        Random rand  = new Random();
        rand.setSeed(2024);

        for (int i = 0; i < 100; i++) {

            for (int j = 0; j < 10; j++) {

                String isbn = isbns.get(rand.nextInt(isbns.size()));

                try {
                    bookshelfService.insertBook("bookshelf"+i, "user" + i, isbn);
                } catch (CustomerNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (BookshelfNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (BookNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (PersonalBookAlreadyExistsException e) {
                }

            }



        }
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

    private ArrayList<String> loadBookData() throws CheckDigitException {

        ArrayList<String> bookData = new ArrayList<>();

        Set<String> genre = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            genre.add("scifi");
        }


        for (int i = 0; i < 100; i++) {

            String isbn = makeISBN();

            bookData.add(isbn);

            BookDTO bookDTO = new BookDTO(
                    isbn,
                    "title"+i,
                    "description" + i,
                    makeDate(),
                    200,
                    "publisher"+(i % 4),
                    genre,
                    "author1",
                    "url"
                    );

            try {
                bookService.insertBook(bookDTO);
            } catch (BookAlreadyExistsException e) {
            } catch (PublisherNotFoundException e) {
                throw new RuntimeException(e);
            } catch (GenresNotFoundException e) {
                throw new RuntimeException(e);
            } catch (AuthorNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvalidISBNExeption e) {
                throw new RuntimeException(e);
            }

        }

        return bookData;

    }

    private void loadAuthor(){

        try {

            RegisterDTO registerDTO = new RegisterDTO("author1", "password1", "email1@gmail.com");
            authenticationService.signup(registerDTO, true);

            registerDTO = new RegisterDTO("author2", "password2", "email2@gmail.com");
            authenticationService.signup(registerDTO, true);

        } catch (CustomerAlreadyExistsException e) {
        }


    }

    private void loadCustomerData() {

        //inserting customers
        for (int i = 0; i < 100; i++) {

            RegisterDTO registerDTO = new RegisterDTO("user" + i, "password" + i, "email" + i + "@gmail.com");

            try {
                authenticationService.signup(registerDTO, false);
            } catch (CustomerAlreadyExistsException e) {
            }


        }


    }

    private void loadBookshelfData() {

        for (int i = 0; i < 100; i++) {

            BookShelfCreationDTO bookShelfCreationDTO = new BookShelfCreationDTO("bookshelf" + i, Privacy.values()[i % Privacy.values().length].name());

            try {
                bookshelfService.insertBookshelf(bookShelfCreationDTO, "user" + i);
            } catch (CustomerNotFoundException e) {
            } catch (BookshelfAlreadyExists e) {
            }

        }
    }

}
