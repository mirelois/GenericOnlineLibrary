package com.aa.coolreads.User.mappers;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.User.dto.NotificationCreationDTO;
import com.aa.coolreads.User.dto.NotificationDTO;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.Notification;
import com.aa.coolreads.User.models.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDTO toNotificationDTO(Notification notification) {
        Book book = notification.getRelatedBook();
        Customer customer = notification.getCustomerCreator();

        if (book != null) {
            return new NotificationDTO(notification.getNotificationType(), notification.getId(), customer.getUsername(), book.getTitle(), book.getAuthor().getUsername(), notification.getCreatedAt());
        }else{
            return new NotificationDTO(notification.getNotificationType(), notification.getId(), customer.getUsername(), notification.getCreatedAt());
        }
    }

    public Notification toNotification(NotificationCreationDTO notificationCreationDTO, Customer customerCreator, Customer customer) {
        return new Notification(customer, customerCreator, notificationCreationDTO.getNotificationType());
    }

    public Notification toNotification(NotificationType notificationType, Book book, Customer customerCreator, Customer customer) {
        Notification notification = new Notification(customer, customerCreator, notificationType);
        notification.setRelatedBook(book);
        return notification;
    }
}
