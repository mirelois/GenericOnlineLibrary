package com.aa.coolreads.User.mappers;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
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

    public BookShelfCreationDTO bookShelfCreationDTO(Bookshelf bookshelf){
        return new BookShelfCreationDTO(bookshelf.getName(), bookshelf.getPrivacy().name());
    }

    public PersonalBookDTO toPersonalBookDTO(PersonalBook personalBook){
        return new PersonalBookDTO(personalBook.getPagesRead(), personalBook.getInsertDate(), personalBook.getBook().getIsbn());
    }

    public PersonalBook toPersonalBook(PersonalBookDTO personalBookDTO, Book book, Bookshelf bookshelf){
        return new PersonalBook(personalBookDTO.getPagesRead(), personalBookDTO.getInsertDate(), book, bookshelf);
    }
}
