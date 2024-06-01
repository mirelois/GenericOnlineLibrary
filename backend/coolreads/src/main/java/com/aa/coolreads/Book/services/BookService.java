package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.components.BookMapper;
import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.exception.BookAlreadyExistsException;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.exception.GenresNotFoundException;
import com.aa.coolreads.Book.exception.PublisherNotFoundException;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.Book.models.Publisher;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.Book.repositories.GenreRepository;
import com.aa.coolreads.Book.repositories.PublisherRepository;
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

    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository, GenreRepository genreRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO getBookByISBN(String isbn) throws BookNotFoundException {
        Optional<Book> bookOptional = this.bookRepository.findById(isbn);
        if(bookOptional.isPresent()){
            return this.bookMapper.toBookDTO(bookOptional.get());
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

    public void insertBook(BookDTO bookDTO) throws BookAlreadyExistsException, PublisherNotFoundException, GenresNotFoundException {

        checkIfDoesntBookExist(bookDTO.getIsbn());

        Publisher publisher = checkIfPublisherExist(bookDTO.getPublisherName());

        Set<Genre> genres = checkIfGenresExist(bookDTO.getGenres());

        this.bookRepository.save(this.bookMapper.toBook(bookDTO, publisher, genres));
    }
}
