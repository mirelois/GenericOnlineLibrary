package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.BookReviewCommentDTO;
import com.aa.coolreads.Book.dto.BookReviewDTO;
import com.aa.coolreads.Book.dto.SimpleReviewDTO;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.exception.InsufficientReviewParametersException;
import com.aa.coolreads.Book.exception.ReviewNotFoundException;
import com.aa.coolreads.Book.mappers.FullBookMapper;
import com.aa.coolreads.Book.models.*;
import com.aa.coolreads.Book.repositories.BookRatingRepository;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.Book.repositories.BookReviewLikeRepository;
import com.aa.coolreads.Book.repositories.BookReviewRepository;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookReviewService {
    private final BookReviewRepository bookReviewRepository;

    private final BookReviewLikeRepository reviewLikeRepository;

    private final BookRatingRepository bookRatingRepository;

    private final BookRepository bookRepository;

    private final CustomerRepository customerRepository;

    private final FullBookMapper bookMapper;

    @Autowired
    public BookReviewService(BookReviewRepository bookReviewRepository, BookReviewLikeRepository reviewLikeRepository, BookRatingRepository bookRatingRepository, BookRepository bookRepository, CustomerRepository customerRepository, FullBookMapper bookMapper){
        this.bookReviewRepository = bookReviewRepository;
        this.reviewLikeRepository = reviewLikeRepository;
        this.bookRatingRepository = bookRatingRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.bookMapper = bookMapper;
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
    public void insertReview(String isbn, String username, SimpleReviewDTO simpleReviewDTO) throws BookNotFoundException, CustomerNotFoundException {
        Book book = this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        this.bookReviewRepository.save(this.bookMapper.toReview(simpleReviewDTO, customer, book));
    }

    @Transactional
    public void updateReview(String isbn, String username, SimpleReviewDTO simpleReviewDTO) throws BookNotFoundException, CustomerNotFoundException, ReviewNotFoundException, InsufficientReviewParametersException {
        Book book = this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

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
        this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        this.bookReviewRepository.deleteById(new ReviewId(username, isbn));
    }

    @Transactional
    public void insertLike(String isbn, String review_username, String likeType) throws BookNotFoundException, CustomerNotFoundException, IllegalArgumentException, ReviewNotFoundException {
        this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));
        Review review = this.bookReviewRepository.findById(new ReviewId(review_username, isbn)).orElseThrow(() -> new ReviewNotFoundException(isbn, review_username));

        LikeType likeTypeEnum;
        try {
            likeTypeEnum = LikeType.valueOf(likeType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid like type: " + likeType);
        }

        this.reviewLikeRepository.save(new ReviewLike(likeTypeEnum, customer, review));
    }

    @Transactional
    public void deleteLike(String isbn, String review_username) throws BookNotFoundException, CustomerNotFoundException, ReviewNotFoundException {
        this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));
        Review review = this.bookReviewRepository.findById(new ReviewId(review_username, isbn)).orElseThrow(() -> new ReviewNotFoundException(isbn, review_username));

        this.reviewLikeRepository.deleteById(new ReviewLikeId(customer, review));
    }
}
