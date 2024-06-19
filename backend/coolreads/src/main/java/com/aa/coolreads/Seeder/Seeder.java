package com.aa.coolreads.Seeder;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.dto.PublisherDTO;
import com.aa.coolreads.Book.exception.*;
import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.Book.services.BookService;
import com.aa.coolreads.Book.services.PublisherService;
import com.aa.coolreads.User.dto.*;
import com.aa.coolreads.User.exception.*;
import com.aa.coolreads.User.mappers.BookshelfMapper;
import com.aa.coolreads.User.mappers.CustomerMapper;
import com.aa.coolreads.User.models.*;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.services.AuthenticationService;
import com.aa.coolreads.User.services.BookshelfService;
import com.aa.coolreads.User.services.CustomerService;
import com.aa.coolreads.User.services.NotificationService;
import jakarta.transaction.Transactional;
import org.apache.commons.validator.routines.ISBNValidator;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.ISBNCheckDigit;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("test")
public class Seeder implements CommandLineRunner {

    private final BookshelfService bookshelfService;
    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthenticationService authenticationService;
    private final NotificationService notificationService;
    private final CustomerService customerService;

    public Seeder(BookshelfService bookshelfService, BookService bookService, PublisherService publisherService, AuthenticationService authenticationService, NotificationService notificationService, CustomerService customerService) {
        this.bookshelfService = bookshelfService;
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authenticationService = authenticationService;
        this.notificationService = notificationService;
        this.customerService = customerService;
    }


    private static String makeISBN(Random rand) throws CheckDigitException {

        StringBuilder prefix = new StringBuilder("978");

        for (int i = 0; i < 9; i++) {
            prefix.append(rand.nextInt(10));
        }

        ISBNCheckDigit isbnCheckDigit = new ISBNCheckDigit();

        System.out.println(prefix);

        String digit = isbnCheckDigit.calculate(prefix.toString());

        return prefix + digit;

    }

    private static Date makeDate(Random rand) {

        int year = rand.nextInt( 120);

        int month = rand.nextInt(12);

        int day;

        if (month != 1) {
            day = rand.nextInt(31) + 1;
        } else {
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
        enrichCustomers();
        addFriends();
        loadNotifications(isbns);
    }

    private void loadPersonalBookData(ArrayList<String> isbns) {

        Random rand = new Random();
        rand.setSeed(2024);

        for (int i = 0; i < 100; i++) {

            for (int j = 0; j < 10; j++) {

                String isbn = isbns.get(rand.nextInt(isbns.size()));

                try {
                    bookshelfService.insertBook(DefaultBookshelf.already_read.name(), "user" + i, isbn);
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

    private void loadPublisher() {

        for (int i = 0; i < 4; i++) {
            PublisherDTO publisherDTO = new PublisherDTO("publisher" + i, "email" + i + "@gmail.com", "siteurl" + i, "logourl" + i);
            try {
                publisherService.insertPublisher(publisherDTO);
            } catch (PublisherAlreadyExistsException e) {
            }
        }

    }

    private ArrayList<String> loadBookData() throws CheckDigitException {

        Random rand = new Random();
        rand.setSeed(2024);

        ArrayList<String> bookData = new ArrayList<>();

        Set<String> genre = new HashSet<>();

        String genres[] = {"scifi", "fantasy", "adventure", "mystery", "biography", "manga", "music", "sports", "romance"};

        for (int i = 0; i < rand.nextInt(genres.length); i++) {
            genre.add(genres[rand.nextInt(genres.length)]);
        }


        for (int i = 0; i < 100; i++) {

            String isbn = makeISBN(rand);

            bookData.add(isbn);

            BookDTO bookDTO = new BookDTO(
                    isbn,
                    "title" + i,
                    "description" + i,
                    makeDate(rand),
                    200,
                    "publisher" + (i % 4),
                    genre,
                    "author1",
                    "https://picsum.photos/id/" + rand.nextInt(1000) + "/650/870"
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

    private void loadAuthor() {

        try {

            RegisterDTO registerDTO = new RegisterDTO("author1", "password1", "email1@gmail.com");
            authenticationService.signup(registerDTO, true);

            registerDTO = new RegisterDTO("author2", "password2", "email2@gmail.com");
            authenticationService.signup(registerDTO, true);

        } catch (CustomerAlreadyExistsException e) {
        }
    }

    private void enrichCustomers() {

        Random rand = new Random();
        rand.setSeed(2024);

        Gender genders[] = Gender.values();

        String countries[] = {"Portugal", "Brazil", "USA", "Canada", "France", "UK"};

        for (int i = 0; i < 100; i++) {

            BookShelfDTO bookShelfDTO = new BookShelfDTO("currently_reading", Privacy.PUBLIC.name(), new HashSet<PersonalBookDTO>());

            SimpleCustomerDTO simpleCustomerDTO = new SimpleCustomerDTO("user" + i,
                    genders[rand.nextInt(genders.length)].name(),
                    "they/them",
                    makeDate(rand),
                    countries[rand.nextInt(countries.length)],
                    "description" + i,
                    "interests" + i,
                    "https://randomuser.me/api/portraits/male/" + rand.nextInt(100) + ".jpg",
                    "https://picsum.photos/id/" + rand.nextInt(1000) + "/650/870",
                    bookShelfDTO);

            try {
                customerService.updateMyCustomerProfile(simpleCustomerDTO, "user" + i);
            } catch (CustomerNotFoundException e) {
                throw new RuntimeException(e);
            } catch (BookshelfNotFoundException e) {
                throw new RuntimeException(e);
            }

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

    private void addFriends() {

        Random rand = new Random();
        rand.setSeed(2024);

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 10; j++) {
                String friend = "user" + rand.nextInt(100);
                try {
                    notificationService.sendFriendRequestNotification("user" + i, friend);
                    notificationService.addFriend(friend, "user" + i);
                } catch (CustomerNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (NoFriendRequestFromRequestedCustomerException e) {
                    throw new RuntimeException(e);
                }
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

    public void loadNotifications(ArrayList<String> isbns) {

        Random rand = new Random();
        rand.setSeed(2024);

        for (int i = 0; i < 100; i++) {

            for (int j = 0; j < 50; j++) {

                try {
                    NotificationType notificationType = NotificationType.values()[j % NotificationType.values().length];

                    if (notificationType == NotificationType.FRIEND_REVIEWED_BOOK_NOTIFICATION | notificationType == NotificationType.FRIEND_STARRED_BOOK_NOTIFICATION) {
                        notificationService.insertBookRelatedNotification(notificationType, isbns.get(rand.nextInt(100)), "user" + i);
                    }else{
                        NotificationCreationDTO notificationCreationDTO = new NotificationCreationDTO(notificationType);
                        notificationService.insertNotification(notificationCreationDTO, "user" + i);
                    }

                } catch (CustomerNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (BookNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }

}
