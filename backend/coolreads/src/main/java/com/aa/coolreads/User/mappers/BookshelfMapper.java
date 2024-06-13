package com.aa.coolreads.User.mappers;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
import com.aa.coolreads.User.dto.SimpleBookShelfDTO;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.PersonalBook;
import com.aa.coolreads.User.models.Privacy;
import org.springframework.stereotype.Component;

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
}
