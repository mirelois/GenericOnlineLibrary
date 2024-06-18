package com.aa.coolreads.User.mappers;

import com.aa.coolreads.User.dto.BookShelfDTO;
import com.aa.coolreads.User.dto.ExclusivityClassDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
import com.aa.coolreads.User.dto.SimpleBookShelfDTO;
import com.aa.coolreads.User.models.*;
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

    public BookShelfDTO toBookShelfDTO(Bookshelf bookshelf){
        return new BookShelfDTO(bookshelf.getName(), bookshelf.getPrivacy().name(), bookshelf.getPersonalBooks().stream().map(this::toPersonalBookDTO).collect(Collectors.toSet()));
    }

    public ExclusivityClassDTO toExclusivityClassDTO(ExclusivityClass exclusivityClass){
        return new ExclusivityClassDTO(exclusivityClass.getName(), exclusivityClass.getBookshelves().stream().map(Bookshelf::getName).collect(Collectors.toSet()));
    }
}
