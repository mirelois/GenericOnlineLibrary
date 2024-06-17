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
import org.springframework.stereotype.Service;

import java.util.*;
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
        Set<SimpleBookShelfDTO> simpleBookShelfDTOS = this.bookshelfRepository.findBookshelvesByCustomer(customer).stream().map(bookshelf -> this.bookshelfMapper.toSimpleBookShelfDTO(bookshelf, this.personalBooksRepository.getBooksSize(bookshelf))).collect(Collectors.toSet());
        simpleBookShelfDTOS.add(new SimpleBookShelfDTO("all", Privacy.PUBLIC.name(), this.personalBooksRepository.getBookSizeByCustomer(customer)));
        return simpleBookShelfDTOS;
    }

    @Transactional
    public void insertBookshelf(BookShelfCreationDTO bookShelfCreationDTO, String username) throws IllegalArgumentException, CustomerNotFoundException, BookshelfAlreadyExists {
        Privacy privacy = Privacy.valueOf(bookShelfCreationDTO.getPrivacy().toUpperCase());

        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));
        if(Objects.equals(bookShelfCreationDTO.getName(), "all") || this.bookshelfRepository.findBookshelfByNameAndCustomer(bookShelfCreationDTO.getName(), customer).isPresent())
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

        if(Objects.equals(name, "all"))
            throw new InvalidBookshelfDeletionException(name);

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

    public boolean checkIfExclusivityClassesAreConflictFree(String username, String isbn, String newBookshelfName) throws BookshelfNotFoundException, CustomerNotFoundException, BookNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));
        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(newBookshelfName, customer)
                .orElseThrow(() -> new BookshelfNotFoundException(newBookshelfName));

        ExclusivityClass newExclusivityClass = bookshelf.getExclusivityClass();

        Book book = this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        Optional<PersonalBook> personalBook = this.personalBooksRepository
                .getPersonalBookByBookAndCustomer(book, customer);

        if(personalBook.isEmpty())
            return true;

        boolean isConflictFree = true;

        Iterator<Bookshelf> iterator = personalBook.get().getBookshelves().iterator();
        Bookshelf bookshelfTemp;
        while(iterator.hasNext() && isConflictFree){
            bookshelfTemp = iterator.next();
            if(!Objects.equals(bookshelfTemp.getName(), bookshelf.getName()) && Objects.equals(bookshelfTemp.getExclusivityClass(), newExclusivityClass)){
                isConflictFree = false;
            }
        }

        return isConflictFree;
    }

    @Transactional
    public void insertBook(String name, String username, String isbn) throws CustomerNotFoundException, BookshelfNotFoundException, BookNotFoundException, PersonalBookAlreadyExistsException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(name, customer).orElseThrow(() -> new BookshelfNotFoundException(name));

        Book book = this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        Optional<PersonalBook> optionalPersonalBook = this.personalBooksRepository.getPersonalBookByBookAndCustomer(book, customer);
        PersonalBook personalBook;
        if(optionalPersonalBook.isEmpty()){
            personalBook = new PersonalBook(0, book, customer);
        } else{
            personalBook = optionalPersonalBook.get();
            if(personalBook.getBookshelves().contains(bookshelf))
                throw new PersonalBookAlreadyExistsException(isbn);
        }

        for(Bookshelf personalBookBookshelf: personalBook.getBookshelves()){
            if(Objects.equals(personalBookBookshelf.getExclusivityClass(), bookshelf.getExclusivityClass())){
                personalBook.removeBookshelf(personalBookBookshelf);
            }
        }

        personalBook.addBookshelf(bookshelf);

        this.personalBooksRepository.save(personalBook);
    }

    @Transactional
    public void updateBook(String username, String isbn, Integer pagesRead) throws CustomerNotFoundException, PersonalBookNotFoundException, BookNotFoundException, InvalidPageReadException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));
        Book book = this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        if(pagesRead < 0 || book.getTotalPageNumbers() < pagesRead)
            throw new InvalidPageReadException(pagesRead);

        PersonalBook personalBook = this.personalBooksRepository
                .getPersonalBookByBookAndCustomer(book, customer).orElseThrow(() -> new PersonalBookNotFoundException(isbn));

        personalBook.setPagesRead(pagesRead);
    }

    @Transactional
    public void deleteBook(String username, String isbn) throws CustomerNotFoundException{
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        this.personalBooksRepository.deletePersonalBookByIsbnAndCustomer(isbn, customer);
    }
}
