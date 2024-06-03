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
import com.aa.coolreads.User.repositories.AuthorRepository;
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


    private final PublisherRepository publisherRepository;

    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    private final CustomerRepository customerRepository;

    private final BookMapper bookMapper;

    private final FullBookMapper fullBookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookRatingRepository bookRatingRepository, PublisherRepository publisherRepository, GenreRepository genreRepository, AuthorRepository authorRepository, CustomerRepository customerRepository, BookMapper bookMapper, FullBookMapper fullBookMapper) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.customerRepository = customerRepository;
        this.bookMapper = bookMapper;
        this.fullBookMapper = fullBookMapper;
    }

    public FullBookDTO getBookByISBN(String isbn) throws BookNotFoundException {
        Optional<Book> bookOptional = this.bookRepository.findById(isbn);
        if(bookOptional.isPresent()){
            return this.fullBookMapper.toFullBookDTO(bookOptional.get());
        }

        throw new BookNotFoundException(isbn);
    }

    private void checkIfDoesntBookExist(String isbn) throws BookAlreadyExistsException{
        if(this.bookRepository.findById(isbn).isPresent())
            throw new BookAlreadyExistsException(isbn);
    }

    private Publisher checkIfPublisherExist(String publisherName) throws PublisherNotFoundException{
        Optional<Publisher> publisherOptional = this.publisherRepository.findById(publisherName);
        if(publisherOptional.isEmpty())
            throw new PublisherNotFoundException(publisherName);

        return publisherOptional.get();
    }

    private Set<Genre> checkIfGenresExist(Set<String> genresNames) throws GenresNotFoundException {
        Set<Genre> genres = new HashSet<>();
        Set<String> invalidGenres = new HashSet<>();
        boolean isValid = true;
        Iterator<String> genreIter = genresNames.iterator();
        while(genreIter.hasNext() && isValid){
            String genreName = genreIter.next();

            Optional<Genre> genreOptional = this.genreRepository.findById(genreName);
            if(genreOptional.isEmpty()) {
                invalidGenres.add(genreName);
                isValid = false;
            } else {
                genres.add(genreOptional.get());
            }
        }

        if(!isValid){
            while(genreIter.hasNext()){
                String genreName = genreIter.next();
                if(this.genreRepository.findById(genreName).isEmpty())
                    invalidGenres.add(genreName);
            }

            throw new GenresNotFoundException(invalidGenres);
        }

        return genres;
    }

    private Author checkIfAuthorExist(String userName) throws AuthorNotFoundException {
        Optional<Author> authorOptional = this.authorRepository.findById(userName);
        if(authorOptional.isPresent()){
            return authorOptional.get();
        }

        throw new AuthorNotFoundException(userName);
    }

    public void insertBook(BookDTO bookDTO) throws BookAlreadyExistsException, PublisherNotFoundException, GenresNotFoundException, AuthorNotFoundException {

        checkIfDoesntBookExist(bookDTO.getIsbn());

        Publisher publisher = checkIfPublisherExist(bookDTO.getPublisherName());

        Set<Genre> genres = checkIfGenresExist(bookDTO.getGenres());

        Author author = checkIfAuthorExist(bookDTO.getAuthorUsername());

        this.bookRepository.save(this.bookMapper.toBook(bookDTO, publisher, genres, author));
    }

    public void insertRating(String isbn, BookRatingDTO bookRatingDTO) throws BookNotFoundException, CustomerNotFoundException {
        Optional<Book> bookOptional = this.bookRepository.findById(isbn);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException(isbn);
        }

        String username = bookRatingDTO.getCustomerUserName();
        Optional<Customer> customerOptional = this.customerRepository.findById(username);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException(username);
        }

        Book book = bookOptional.get();
        book.addRating(new BookRating(bookRatingDTO.getRating(), book, customerOptional.get()));

        this.bookRepository.save(book);
    }
}
