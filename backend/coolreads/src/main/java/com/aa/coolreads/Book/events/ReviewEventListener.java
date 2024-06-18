package com.aa.coolreads.Book.events;

import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.models.NotificationType;
import com.aa.coolreads.User.services.NotificationService;
import com.aa.coolreads.User.services.TestMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewEventListener {

    private final NotificationService notificationService;

    private static final Logger logger = LoggerFactory.getLogger(TestMailService.class);

    public ReviewEventListener(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @EventListener
    public void handleReviewEvent(ReviewEvent reviewEvent){
        ReviewMessage reviewMessage = reviewEvent.getReviewMessage();
        try {
            notificationService.insertBookRelatedNotification(NotificationType.FRIEND_REVIEWED_BOOK_NOTIFICATION, reviewMessage.getUsername(), reviewMessage.getUsername());
        } catch (CustomerNotFoundException | BookNotFoundException e) {
            logger.error("ReviewEventListener:{}", e.getMessage());
        }
    }
}
