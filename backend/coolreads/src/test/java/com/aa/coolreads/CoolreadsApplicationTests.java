package com.aa.coolreads;

import com.aa.coolreads.Book.dto.*;
import com.aa.coolreads.Book.exception.*;
import com.aa.coolreads.Book.mappers.StatisticsMapper;
import com.aa.coolreads.Book.models.TimeFrame;
import com.aa.coolreads.Book.services.BookReviewService;
import com.aa.coolreads.Book.services.BookService;
import com.aa.coolreads.Book.services.StatisticService;
import com.aa.coolreads.User.dto.NotificationCreationDTO;
import com.aa.coolreads.User.dto.NotificationDTO;
import com.aa.coolreads.User.dto.PostCreationDTO;
import com.aa.coolreads.User.dto.PostDTO;
import com.aa.coolreads.User.exception.AuthorNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.models.DefaultBookshelf;
import com.aa.coolreads.User.models.NotificationType;
import com.aa.coolreads.User.services.AgeSlice;
import com.aa.coolreads.User.services.CountrySlice;
import com.aa.coolreads.User.services.GenderSlice;
import com.aa.coolreads.User.services.Sliceable;
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
    @Autowired
    private StatisticsMapper statisticsMapper;

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
                "9783161484100",
                "this is a book",
                "this is a book description",
                new Date(2024, 12, 20),
                331,
                "publisher0",
                genres,
                "author1",
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
                "publisher0",
                genres,
                "author1",
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
            FullBookDTO fullBookDTO = bookService.getBookByISBN("9780660599960");
//            assert Objects.equals(fullBookDTO.getPublisherName(), "Science Today Press");
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
                    "9780660599960",
                    "user0",
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
            postService.insertPost(postDTO, "user0");
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }

        Set<PostDTO> posts =  postService.getPostsByUsername("user0", 0, 10);

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

        Set<PostDTO> posts =  postService.getPostsByUsername("user0", 0, 10);

//        assert posts.isEmpty();

    }

    @Test
    void testNotificationOperations() throws CustomerNotFoundException {

        NotificationCreationDTO notificationCreationDTO = new NotificationCreationDTO(NotificationType.FRIEND_REQUEST_NOTIFICATION);

        try {
            notificationService.insertNotification(notificationCreationDTO, "user0");
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }

        Set<NotificationDTO> notifications = notificationService.getNotificationsByUserName("user0", 0, 10);

//        assert notifications.size() == 1;

        for (NotificationDTO notificationDTO : notifications) {
            notificationService.deleteNotification("user0", notificationDTO.getId());
        }

    }

    @Test
    void testNotificationOperationsExeption() throws CustomerNotFoundException {

        NotificationCreationDTO notificationCreationDTO = new NotificationCreationDTO(NotificationType.FRIEND_REQUEST_NOTIFICATION);

        try {
            notificationService.insertNotification(notificationCreationDTO, "techguru_that_dont_exist");
            assert false;
        } catch (CustomerNotFoundException e) {
            //throw new RuntimeException(e);
        }

        Set<NotificationDTO> notifications = notificationService.getNotificationsByUserName("user0", 0, 10);

//        assert notifications.isEmpty();

    }

    //-----------------Statistics tests----------------------

    @Test
    void testStatisticsNumber(){

        StatisticsNumberDTO numbers = statisticService.getStatisticsNumbers("9780660599960");

        System.out.println(numbers.getAlreadyRead());
        System.out.println(numbers.getCurrentlyReading());
        System.out.println(numbers.getDidNotFinish());
        System.out.println(numbers.getWantToRead());

    }

    @Test
    void testStatisticsSlice(){

        Sliceable sliceable = new AgeSlice(this.statisticService);
        StatisticsChartDTO statisticsPieChartDTOAge = statisticsMapper.toStatisticsPieChartDTO(sliceable.getSlices(DefaultBookshelf.already_read, "9781673363609"));

        sliceable = new CountrySlice(this.statisticService);
        StatisticsChartDTO statisticsPieChartDTOCountry = statisticsMapper.toStatisticsPieChartDTO(sliceable.getSlices(DefaultBookshelf.already_read, "9781673363609"));

        sliceable = new GenderSlice(this.statisticService);
        StatisticsChartDTO statisticsPieChartDTOGender = statisticsMapper.toStatisticsPieChartDTO(sliceable.getSlices(DefaultBookshelf.already_read, "9781673363609"));
    }

    @Test
    void testNotificationGet(){
        try {
            Set<NotificationDTO> notificationDTOS =  notificationService.getNotificationsByUserName("user0", 0, 10);
            System.out.println(notificationDTOS);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testLineChart(){
        StatisticsChartDTO line = statisticService.getTimeLineChart(DefaultBookshelf.already_read, "9780660599960", TimeFrame.month, 0, 10);
    }

}

