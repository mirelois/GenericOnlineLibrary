package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.NotificationType;

public class NotificationCreationDTO {

    private NotificationType notificationType;

    public NotificationCreationDTO() {}

    public NotificationCreationDTO(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}
