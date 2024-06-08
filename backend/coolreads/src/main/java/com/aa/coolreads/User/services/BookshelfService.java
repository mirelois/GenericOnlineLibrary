package com.aa.coolreads.User.services;

import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
import com.aa.coolreads.User.dto.SimpleBookShelfDTO;
import com.aa.coolreads.User.exception.BookshelfNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.mappers.BookshelfMapper;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.PersonalBook;
import com.aa.coolreads.User.models.Privacy;
import com.aa.coolreads.User.repositories.BookshelfRepository;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.repositories.PersonalBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE); // deixar assim enquanto o joão não quiser paginação XD

        return this.bookshelfRepository.findBookshelvesByCustomer(customer, pageRequest).get().map(bookshelf -> this.bookshelfMapper.toSimpleBookShelfDTO(bookshelf, this.personalBooksRepository.getBooksSize(bookshelf))).collect(Collectors.toSet());
    }

    @Transactional
    public void insertBookshelf(BookShelfCreationDTO bookShelfCreationDTO, String username) throws IllegalArgumentException, CustomerNotFoundException {
        Privacy privacy = Privacy.valueOf(bookShelfCreationDTO.getPrivacy().toUpperCase());

        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        this.bookshelfRepository.save(this.bookshelfMapper.toBookshelf(bookShelfCreationDTO.getName(), privacy, customer));
    }

    @Transactional
    public void updateBookshelf(String name, BookShelfCreationDTO bookShelfCreationDTO, String username) throws IllegalArgumentException, CustomerNotFoundException, BookshelfNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(name, customer).orElseThrow(() -> new BookshelfNotFoundException(name));

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
    public Set<PersonalBookDTO> getBooks(String name, String username) throws CustomerNotFoundException, BookshelfNotFoundException {//, Integer page, Integer size
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(name, customer).orElseThrow(() -> new BookshelfNotFoundException(name));

        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        return this.personalBooksRepository.findBooks(bookshelf,pageRequest).get().map(this.bookshelfMapper::toPersonalBookDTO).collect(Collectors.toSet());//, pageRequest
    }

    @Transactional
    public void insertBook(String name, String username, PersonalBookDTO personalBookDTO) throws CustomerNotFoundException, BookshelfNotFoundException, BookNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(name, customer).orElseThrow(() -> new BookshelfNotFoundException(name));

        Book book = this.bookRepository.findById(personalBookDTO.getBookISBN()).orElseThrow(() -> new BookNotFoundException(personalBookDTO.getBookISBN()));

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
