package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.exception.BookshelfNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.mappers.BookshelfMapper;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.Privacy;
import com.aa.coolreads.User.repositories.BookshelfRepository;
import com.aa.coolreads.User.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookshelfService {

    private final BookshelfRepository bookshelfRepository;

    private final CustomerRepository customerRepository;

    private final BookshelfMapper bookshelfMapper;

    public BookshelfService(BookshelfRepository bookshelfRepository, CustomerRepository customerRepository, BookshelfMapper bookshelfMapper){
        this.bookshelfRepository = bookshelfRepository;
        this.customerRepository = customerRepository;
        this.bookshelfMapper = bookshelfMapper;
    }

    @Transactional
    public BookShelfCreationDTO getBookshelf(String name, String username) throws CustomerNotFoundException, BookshelfNotFoundException {
        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        Bookshelf bookshelf = this.bookshelfRepository.findBookshelfByNameAndCustomer(name, customer).orElseThrow(() -> new BookshelfNotFoundException(name));

        return this.bookshelfMapper.bookShelfCreationDTO(bookshelf);
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
}
