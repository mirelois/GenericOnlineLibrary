package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.BookReviewDTO;
import com.aa.coolreads.Book.dto.FullBookDTO;
import com.aa.coolreads.Book.dto.SimpleReviewDTO;
import com.aa.coolreads.Book.exception.*;
import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.mappers.FullBookMapper;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.Book.models.Publisher;
import com.aa.coolreads.Book.models.Review;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.Book.repositories.BookReviewRepository;
import com.aa.coolreads.Book.repositories.GenreRepository;
import com.aa.coolreads.Book.repositories.PublisherRepository;
import com.aa.coolreads.User.exception.AuthorNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.models.Author;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    private final BookReviewRepository bookReviewRepository;

    private final PublisherRepository publisherRepository;

    private final GenreRepository genreRepository;

    private final CustomerRepository customerRepository;

    private final FullBookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookReviewRepository bookReviewRepository, PublisherRepository publisherRepository, GenreRepository genreRepository, CustomerRepository customerRepository, FullBookMapper fullBookMapper) {
        this.bookRepository = bookRepository;
        this.bookReviewRepository = bookReviewRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
        this.customerRepository = customerRepository;
        this.bookMapper = fullBookMapper;
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

    @Transactional
    public FullBookDTO getBookByISBN(String isbn) throws BookNotFoundException {
        Book book = findBookByIsbn(isbn);

        double averageRating = this.bookReviewRepository.getBookAverageRating(isbn);

        return this.bookMapper.toFullBookDTO(book, averageRating);
    }

    private void checkIfValidISBN(String isbn) throws InvalidISBNExeption {

        ISBNValidator isbnValidator = new ISBNValidator();

        if (!isbnValidator.isValid(isbn)){
            throw new InvalidISBNExeption(isbn);
        }

    }

    @Transactional
    public void insertBook(BookDTO bookDTO) throws BookAlreadyExistsException, PublisherNotFoundException, GenresNotFoundException, AuthorNotFoundException, InvalidISBNExeption {

        checkIfValidISBN(bookDTO.getIsbn());

        checkIfBookDoesntExist(bookDTO.getIsbn());

        Publisher publisher = findPublisherByName(bookDTO.getPublisherName());

        Set<Genre> genres = findGenresByGenreNames(bookDTO.getGenres());

        Author author = findAuthorByUsername(bookDTO.getAuthorUsername());

        this.bookRepository.save(this.bookMapper.toBook(bookDTO, publisher, genres, author));
    }

    private void checkIfValidRating(Double rating) throws InvalidRatingExeption {
        if (rating < 0 || rating > 5.0){
            if (!(rating % 0.5 == 0)){
                throw new InvalidRatingExeption(rating);
            }
        }else{
            throw new InvalidRatingExeption(rating);
        }
    }

    public Set<BookReviewDTO> getReviews(String isbn, Integer pageNumber, Integer pageSize) {

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<Review> reviewsPage = this.bookReviewRepository.findByIsbn(isbn, pageable);

        return reviewsPage.stream().map(this.bookMapper::toBookReviewDTO).collect(Collectors.toSet());
    }

    @Transactional
    public void insertReview(String isbn, String username, SimpleReviewDTO simpleReviewDTO) throws BookNotFoundException, CustomerNotFoundException, InvalidRatingExeption {

        checkIfValidRating(simpleReviewDTO.getRating());

        Book book = findBookByIsbn(isbn);

        Customer customer = findCustomerByUsername(username);

        book.addReview(this.bookMapper.toReview(simpleReviewDTO, customer, book));

        this.bookRepository.save(book);
    }

    /*
    @Transactional
    public void deleteRating(String isbn, String customerUsername) throws BookNotFoundException, CustomerNotFoundException {
        Book book = findBookByIsbn(isbn);

        Customer customer = findCustomerByUsername(customerUsername);

        this.bookRatingRepository.deleteRating(book, customer);
    }

     */
}
