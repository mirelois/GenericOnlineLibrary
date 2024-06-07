package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.*;
import com.aa.coolreads.Book.exception.*;
import com.aa.coolreads.Book.mappers.FullBookMapper;
import com.aa.coolreads.Book.models.*;
import com.aa.coolreads.Book.repositories.*;
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

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    private final BookReviewRepository bookReviewRepository;

    private final BookRatingRepository bookRatingRepository;

    private final PublisherRepository publisherRepository;

    private final GenreRepository genreRepository;

    private final CustomerRepository customerRepository;

    private final FullBookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookReviewRepository bookReviewRepository, BookRatingRepository bookRatingRepository, PublisherRepository publisherRepository, GenreRepository genreRepository, CustomerRepository customerRepository, FullBookMapper fullBookMapper) {
        this.bookRepository = bookRepository;
        this.bookReviewRepository = bookReviewRepository;
        this.bookRatingRepository = bookRatingRepository;
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

        double averageRating = this.bookRatingRepository.getBookAverageRating(isbn);

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

    private void checkIfValidRating(Double rating) throws InvalidRatingException {
        if (rating == null || rating < 0.5 || rating > 5.0 || rating % 0.5 != 0)
            throw new InvalidRatingException(rating);
    }

    @Transactional
    public Set<BookReviewDTO> getReviews(String isbn, Integer pageNumber, Integer pageSize) {

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<Review> reviewsPage = this.bookReviewRepository.findByIsbn(isbn, pageable);

        return reviewsPage.get().map(review -> {
            BookReviewDTO bookReviewDTO = this.bookMapper.toBookReviewDTO(review, this.bookReviewRepository.getReviewCommentSize(review));
            Optional<Double> rating = this.bookRatingRepository.findByBookIsbnAndUsername(isbn, bookReviewDTO.getCustomerUsername());
            rating.ifPresent(bookReviewDTO::setRating);
            return bookReviewDTO;
        }).collect(Collectors.toSet());
    }

    @Transactional
    public Set<BookReviewCommentDTO> getReviewComments(String isbn, String review_username, Integer pageNumber, Integer pageSize){

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<ReviewComment> commentsPage = this.bookReviewRepository.findCommentByReview(isbn, review_username, pageable);

        return commentsPage.get().map(this.bookMapper::toReviewCommentDTO).collect(Collectors.toSet());
    }

    @Transactional
    public void insertReview(String isbn, String username, SimpleReviewDTO simpleReviewDTO) throws BookNotFoundException, CustomerNotFoundException{

        Book book = findBookByIsbn(isbn);

        Customer customer = findCustomerByUsername(username);

        this.bookReviewRepository.save(this.bookMapper.toReview(simpleReviewDTO, customer, book));
    }

    @Transactional
    public void updateReview(String isbn, String username, SimpleReviewDTO simpleReviewDTO) throws BookNotFoundException, CustomerNotFoundException, ReviewNotFoundException, InsufficientReviewParametersException {
        Book book = findBookByIsbn(isbn);
        Customer customer = findCustomerByUsername(username);

        Review review = this.bookReviewRepository.findById(new ReviewId(customer.getUsername(), book.getIsbn())).orElseThrow(() -> new ReviewNotFoundException(isbn, username));

        String description = simpleReviewDTO.getDescription();
        Date postDate = simpleReviewDTO.getPostDate();
        if(description==null || postDate==null)
            throw new InsufficientReviewParametersException(description, postDate);

        review.setDescription(description);
        review.setPostDate(postDate);
        this.bookReviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(String isbn, String username) throws CustomerNotFoundException, BookNotFoundException {
        findBookByIsbn(isbn);
        findCustomerByUsername(username);

        this.bookReviewRepository.deleteById(new ReviewId(username, isbn));
    }

    @Transactional
    public RatingDTO getRating(String isbn, String username) throws RatingNotFoundException {
        Rating rating = this.bookRatingRepository.findById(new RatingId(isbn, username)).orElseThrow(() -> new RatingNotFoundException(isbn, username));
        return this.bookMapper.toRatingDTO(rating);
    }

    @Transactional
    public void insertRating(String isbn, String username, Double rating) throws BookNotFoundException, CustomerNotFoundException, InvalidRatingException {

        checkIfValidRating(rating);

        Book book = findBookByIsbn(isbn);

        Customer customer = findCustomerByUsername(username);

        this.bookRatingRepository.save(this.bookMapper.toRating(rating, book, customer));
    }

    @Transactional
    public void updateRating(String isbn, String username, Double rating) throws BookNotFoundException, CustomerNotFoundException, InvalidRatingException, RatingNotFoundException {

        checkIfValidRating(rating);

        findBookByIsbn(isbn);
        findCustomerByUsername(username);

        Rating ratingEntity = this.bookRatingRepository.findById(new RatingId(isbn, username)).orElseThrow(() -> new RatingNotFoundException(isbn, username));
        ratingEntity.setRating(rating);

        this.bookRatingRepository.save(ratingEntity);
    }

    @Transactional
    public void deleteRating(String isbn, String username) throws BookNotFoundException, CustomerNotFoundException{
        findBookByIsbn(isbn);
        findCustomerByUsername(username);

        this.bookRatingRepository.deleteById(new RatingId(isbn, username));
    }
}
