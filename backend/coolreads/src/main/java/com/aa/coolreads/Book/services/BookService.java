package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.BookRatingDTO;
import com.aa.coolreads.Book.dto.FullBookDTO;
import com.aa.coolreads.Book.mappers.BookMapper;
import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.exception.BookAlreadyExistsException;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.exception.GenresNotFoundException;
import com.aa.coolreads.Book.exception.PublisherNotFoundException;
import com.aa.coolreads.Book.mappers.FullBookMapper;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.BookRating;
import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.Book.models.Publisher;
import com.aa.coolreads.Book.repositories.BookRatingRepository;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.Book.repositories.GenreRepository;
import com.aa.coolreads.Book.repositories.PublisherRepository;
import com.aa.coolreads.User.exception.AuthorNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.models.Author;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {
    private final BookRepository bookRepository;

    private final BookRatingRepository bookRatingRepository;

    private final PublisherRepository publisherRepository;

    private final GenreRepository genreRepository;

    private final CustomerRepository customerRepository;

    private final BookMapper bookMapper;

    private final FullBookMapper fullBookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookRatingRepository bookRatingRepository, BookRatingRepository bookRatingRepository1, PublisherRepository publisherRepository, GenreRepository genreRepository, CustomerRepository customerRepository, BookMapper bookMapper, FullBookMapper fullBookMapper) {
        this.bookRepository = bookRepository;
        this.bookRatingRepository = bookRatingRepository1;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
        this.customerRepository = customerRepository;
        this.bookMapper = bookMapper;
        this.fullBookMapper = fullBookMapper;
    }

    private void checkIfBookDoesntExist(String isbn) throws BookAlreadyExistsException{
        if(this.bookRepository.findById(isbn).isPresent())
            throw new BookAlreadyExistsException(isbn);
    }

    private Publisher findPublisherByName(String publisherName) throws PublisherNotFoundException {
        return this.publisherRepository.findById(publisherName)
                .orElseThrow(() -> new PublisherNotFoundException(publisherName));
    }

    private Book findBookByIsbn(String isbn) throws BookNotFoundException {
        return this.bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    private Customer findCustomerByUsername(String username) throws CustomerNotFoundException {
        return this.customerRepository.findById(username)
                .orElseThrow(() -> new CustomerNotFoundException(username));
    }

    private Author findAuthorByUsername(String username) throws AuthorNotFoundException {
        try {
            Customer customer = findCustomerByUsername(username);
            if (customer instanceof Author) {
                return (Author) customer;
            } else {
                throw new AuthorNotFoundException(username);
            }
        } catch (CustomerNotFoundException e){
            throw new AuthorNotFoundException(username);
        }
    }

    private Set<Genre> findGenresByGenreNames(Set<String> genreNames) throws GenresNotFoundException {
        Set<Genre> genres = new HashSet<>();
        Set<String> invalidGenres = new HashSet<>();

        for (String genreName : genreNames) {
            Optional<Genre> genreOptional = this.genreRepository.findById(genreName.toLowerCase());
            if (genreOptional.isPresent()) {
                genres.add(genreOptional.get());
            } else {
                invalidGenres.add(genreName);
            }
        }

        if (!invalidGenres.isEmpty()) {
            throw new GenresNotFoundException(invalidGenres);
        }

        return genres;
    }

    public FullBookDTO getBookByISBN(String isbn) throws BookNotFoundException {
        Book book = findBookByIsbn(isbn);

        return this.fullBookMapper.toFullBookDTO(book);
    }

    public void insertBook(BookDTO bookDTO) throws BookAlreadyExistsException, PublisherNotFoundException, GenresNotFoundException, AuthorNotFoundException {

        checkIfBookDoesntExist(bookDTO.getIsbn());

        Publisher publisher = findPublisherByName(bookDTO.getPublisherName());

        Set<Genre> genres = findGenresByGenreNames(bookDTO.getGenres());

        Author author = findAuthorByUsername(bookDTO.getAuthorUsername());

        this.bookRepository.save(this.bookMapper.toBook(bookDTO, publisher, genres, author));
    }

    public void insertRating(String isbn, BookRatingDTO bookRatingDTO) throws BookNotFoundException, CustomerNotFoundException {
        Book book = findBookByIsbn(isbn);

        Customer customer = findCustomerByUsername(bookRatingDTO.getCustomerUserName());

        book.addRating(new BookRating(bookRatingDTO.getRating(), book, customer));

        this.bookRepository.save(book);
    }

    public void updateRating(String isbn, BookRatingDTO bookRatingDTO) throws BookNotFoundException, CustomerNotFoundException {
        Book book = findBookByIsbn(isbn);

        Customer customer = findCustomerByUsername(bookRatingDTO.getCustomerUserName());

        this.bookRatingRepository.updateRating(bookRatingDTO.getRating(), book, customer);
    }

    public void deleteRating(String isbn, String customerUsername) throws BookNotFoundException, CustomerNotFoundException {
        Book book = findBookByIsbn(isbn);

        Customer customer = findCustomerByUsername(customerUsername);

        this.bookRatingRepository.deleteRating(book, customer);
    }
}
