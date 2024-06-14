package com.aa.coolreads;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.dto.FullBookDTO;
import com.aa.coolreads.Book.dto.SimpleReviewDTO;
import com.aa.coolreads.Book.exception.*;
import com.aa.coolreads.Book.services.BookReviewService;
import com.aa.coolreads.Book.services.BookService;
import com.aa.coolreads.Book.services.StatisticService;
import com.aa.coolreads.User.dto.NotificationCreationDTO;
import com.aa.coolreads.User.dto.NotificationDTO;
import com.aa.coolreads.User.dto.PostCreationDTO;
import com.aa.coolreads.User.dto.PostDTO;
import com.aa.coolreads.User.exception.AuthorNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.models.Notification;
import com.aa.coolreads.User.models.NotificationType;
import com.aa.coolreads.User.models.Post;
import com.aa.coolreads.User.services.NotificationService;
import com.aa.coolreads.User.services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class CoolreadsApplicationTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookReviewService bookReviewService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private StatisticService statisticService;

    @Test
	void contextLoads() {
	}

    @Test
    void testInsertBook() {

        HashSet<String> genres = new HashSet<>();

        genres.add("romance");
        genres.add("Adventure");
        genres.add("SciFi");

        BookDTO bookDTO = new BookDTO(
                "978-3-16-148410-0",
                "this is a book",
                "this is a book description",
                new Date(2024, 12, 20),
                331,
                "TechBooks Publishing",
                genres,
                "jkrowling",
                "a"
        );

        try {
            bookService.insertBook(bookDTO);
        } catch (AuthorNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidISBNExeption e) {
            throw new RuntimeException(e);
        } catch (GenresNotFoundException e) {
            throw new RuntimeException(e);
        } catch (BookAlreadyExistsException e) {
            return;
        } catch (PublisherNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testInsertBook_ISBN() {

        HashSet<String> genres = new HashSet<>();

        genres.add("romance");
        genres.add("Adventure");
        genres.add("SciFi");

        BookDTO bookDTO = new BookDTO(
                "978-0-596-52068-9",
                "this is a book",
                "this is a book description",
                new Date(2024, 12, 20),
                331,
                "TechBooks Publishing",
                genres,
                "jkrowling",
                "a"
        );

        try {
            bookService.insertBook(bookDTO);
            assert false;
        } catch (AuthorNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidISBNExeption e) {
            return;
        } catch (GenresNotFoundException e) {
            throw new RuntimeException(e);
        } catch (BookAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (PublisherNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

	@Test
	void testBookService() {

        try {
            FullBookDTO fullBookDTO = bookService.getBookByISBN("1");
            assert Objects.equals(fullBookDTO.getPublisherName(), "Science Today Press");
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testInsertReview(){

        SimpleReviewDTO reviewDTO = new SimpleReviewDTO(
                "description",
                new Date(2024, 12, 1)
        );

        try {
            bookReviewService.insertReview(
                    "978-3-16-148410-0",
                    "techguru",
                    reviewDTO
            );
        } catch (BookNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //---------Post Tests---------

    @Autowired
    private PostService postService;

    @Test
    void testPostOperations(){

        PostCreationDTO postDTO = new PostCreationDTO("This is a post", "This is the content of the post");

        try {
            postService.insertPost(postDTO, "techguru");
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }

        Set<PostDTO> posts =  postService.getPostsByUsername("techguru", 0, 10);

        assert posts.size() == 1;

        for (PostDTO post : posts) {
            postService.deletePost(post.getId());
        }


    }

    @Test
    void testPostOperationsExeption(){

        PostCreationDTO postDTO = new PostCreationDTO("This is a post", "This is the content of the post");

        try {
            postService.insertPost(postDTO, "techguru_that_dont_exist");
            assert false;
        } catch (CustomerNotFoundException e) {
            //throw new RuntimeException(e);
        }

        Set<PostDTO> posts =  postService.getPostsByUsername("techguru", 0, 10);

        assert posts.isEmpty();

    }

    @Test
    void testNotificationOperations(){

        NotificationCreationDTO notificationCreationDTO = new NotificationCreationDTO(NotificationType.FRIEND_REQUEST_NOTIFICATION);

        try {
            notificationService.insertNotification(notificationCreationDTO, "techguru");
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }

        Set<NotificationDTO> notifications = notificationService.getNotificationsByUserName("techguru", 0, 10);

        assert notifications.size() == 1;

        for (NotificationDTO notificationDTO : notifications) {
            notificationService.deleteNotification(notificationDTO.getId());
        }

    }

    @Test
    void testNotificationOperationsExeption(){

        NotificationCreationDTO notificationCreationDTO = new NotificationCreationDTO(NotificationType.FRIEND_REQUEST_NOTIFICATION);

        try {
            notificationService.insertNotification(notificationCreationDTO, "techguru_that_dont_exist");
            assert false;
        } catch (CustomerNotFoundException e) {
            //throw new RuntimeException(e);
        }

        Set<NotificationDTO> notifications = notificationService.getNotificationsByUserName("techguru", 0, 10);

        assert notifications.isEmpty();

    }

    //-----------------Statistics tests----------------------

    @Test
    void testStatisticsNumber(){

        statisticService.getStatisticsNumbers()

    }

}

