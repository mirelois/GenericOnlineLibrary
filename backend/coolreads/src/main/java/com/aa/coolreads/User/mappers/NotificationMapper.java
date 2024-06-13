package com.aa.coolreads.User.mappers;

import com.aa.coolreads.User.dto.NotificationCreationDTO;
import com.aa.coolreads.User.dto.NotificationDTO;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDTO toNotificationDTO(Notification notification) {
        return new NotificationDTO(notification.getNotificationType(), notification.getId());
    }

    public Notification toNotification(NotificationCreationDTO notificationCreationDTO, Customer customerCreator, Customer customer) {
        return new Notification(customer, customerCreator, notificationCreationDTO.getNotificationType());
    }
}
