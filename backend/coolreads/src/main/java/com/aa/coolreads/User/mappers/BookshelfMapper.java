package com.aa.coolreads.User.mappers;

import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.BookShelfDTO;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.Customer;
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
}
