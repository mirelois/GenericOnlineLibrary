package com.aa.coolreads.User.services;

import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
import com.aa.coolreads.User.dto.SimpleBookShelfDTO;
import com.aa.coolreads.User.exception.*;
import com.aa.coolreads.User.mappers.BookshelfMapper;
import com.aa.coolreads.User.models.*;
import com.aa.coolreads.User.repositories.BookshelfRepository;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.repositories.PersonalBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookshelfService {

    private final BookshelfRepository bookshelfRepository;

    private final PersonalBooksRepository personalBooksRepository;

    private final CustomerRepository customerRepository;

    private final BookRepository bookRepository;

    private final BookshelfMapper bookshelfMapper;

    public BookshelfService(BookshelfRepository bookshelfRepository, PersonalBooksRepository personalBooksRepository, CustomerRepository customerRepository, BookRepository bookRepository, BookshelfMapper bookshelfMapper){
        this.bookshelfRepository = bookshelfRepository;
        this.personalBooksRepository = personalBooksRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.bookshelfMapper = bookshelfMapper;
    }

    @Transactional
    public Set<SimpleBookShelfDTO> getBookshelf(String username) throws CustomerNotFoundException {

        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        return this.bookshelfRepository.findBookshelvesByCustomer(customer).stream().map(bookshelf -> this.bookshelfMapper.toSimpleBookShelfDTO(bookshelf, this.personalBooksRepository.getBooksSize(bookshelf))).collect(Collectors.toSet());
    }

    @Transactional
    public void insertBookshelf(BookShelfCreationDTO bookShelfCreationDTO, String username) throws IllegalArgumentException, CustomerNotFoundException, BookshelfAlreadyExists {
        Privacy privacy = Privacy.valueOf(bookShelfCreationDTO.getPrivacy().toUpperCase());

        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));
        if(this.bookshelfRepository.findBookshelfByNameAndCustomer(bookShelfCreationDTO.getName(), customer).isPresent())
            throw new BookshelfAlreadyExists(bookShelfCreationDTO.getName());

        this.bookshelfRepository.save(this.bookshelfMapper.toBookshelf(bookShelfCreationDTO.getName(), privacy, customer));
    }

    @Transactional
    public void updateBookshelf(BookShelfCreationDTO bookShelfCreationDTO, String username) throws IllegalArgumentException, CustomerNotFoundException, BookshelfNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(bookShelfCreationDTO.getName(), customer).orElseThrow(() -> new BookshelfNotFoundException(bookShelfCreationDTO.getName()));

        if(bookShelfCreationDTO.getPrivacy() != null){
            Privacy privacy = Privacy.valueOf(bookShelfCreationDTO.getPrivacy().toUpperCase());
            bookshelf.setPrivacy(privacy);
        }

        if(bookShelfCreationDTO.getName() != null){
            bookshelf.setName(bookShelfCreationDTO.getName());
        }

        this.bookshelfRepository.save(bookshelf);
    }

    @Transactional
    public void deleteBookshelf(String username, String name) throws CustomerNotFoundException, InvalidBookshelfDeletionException {

        for(DefaultBookshelf defaultBookshelfType : DefaultBookshelf.values()){
            if(defaultBookshelfType.name().equals(name))
                throw new InvalidBookshelfDeletionException(name);
        }

        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        this.bookshelfRepository.deleteBookshelfByCustomerAndName(customer, name);
    }

    @Transactional
    public Set<PersonalBookDTO> getBooks(String name, String username) throws CustomerNotFoundException, BookshelfNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(name, customer).orElseThrow(() -> new BookshelfNotFoundException(name));

        return this.personalBooksRepository.findBooks(bookshelf).stream().map(this.bookshelfMapper::toPersonalBookDTO).collect(Collectors.toSet());
    }

    @Transactional
    public void insertBook(String name, String username, PersonalBookDTO personalBookDTO) throws CustomerNotFoundException, BookshelfNotFoundException, BookNotFoundException, PersonalBookAlreadyExists {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(name, customer).orElseThrow(() -> new BookshelfNotFoundException(name));

        Book book = this.bookRepository.findById(personalBookDTO.getBookISBN()).orElseThrow(() -> new BookNotFoundException(personalBookDTO.getBookISBN()));

        if(this.personalBooksRepository.getPersonalBookByBookAndBookshelf(book, bookshelf).isPresent())
            throw new PersonalBookAlreadyExists(book.getIsbn());

        PersonalBook personalBook = this.bookshelfMapper.toPersonalBook(personalBookDTO, book, bookshelf);

        this.personalBooksRepository.save(personalBook);
    }

    @Transactional
    public void deleteBook(String name, String username, String isbn) throws CustomerNotFoundException, BookshelfNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(name, customer).orElseThrow(() -> new BookshelfNotFoundException(name));

        this.personalBooksRepository.deletePersonalBookByIsbnAndBookshelf(isbn, bookshelf);
    }
}
