package com.aa.coolreads.User.mappers;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.BookShelfDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
import com.aa.coolreads.User.dto.SimpleBookShelfDTO;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.PersonalBook;
import com.aa.coolreads.User.models.Privacy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookshelfMapper {

    public Bookshelf toBookshelf(String name, Privacy privacy, Customer customer){
        return new Bookshelf(name, privacy, customer);
    }

    public SimpleBookShelfDTO toSimpleBookShelfDTO(Bookshelf bookshelf, Integer bookCount){
        return new SimpleBookShelfDTO(bookshelf.getName(), bookshelf.getPrivacy().name(), bookCount);
    }

    public PersonalBookDTO toPersonalBookDTO(PersonalBook personalBook){
        return new PersonalBookDTO(personalBook.getPagesRead(), personalBook.getInsertDate(), personalBook.getBook().getIsbn(), personalBook.getBook().getImageUrl(), personalBook.getBook().getTitle());
    }

    public PersonalBook toPersonalBook(PersonalBookDTO personalBookDTO, Book book, Bookshelf bookshelf){
        return new PersonalBook(personalBookDTO.getPagesRead(), personalBookDTO.getInsertDate(), book, bookshelf);
    }

    public BookShelfDTO toBookShelfDTO(Bookshelf bookshelf){
        return new BookShelfDTO(bookshelf.getName(), bookshelf.getPrivacy().name(), bookshelf.getPersonalBooks().stream().map(this::toPersonalBookDTO).collect(Collectors.toSet()));
    }
}
