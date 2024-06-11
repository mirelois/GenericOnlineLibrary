package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.NotificationType;

public class NotificationDTO extends NotificationCreationDTO{

    private Long id;

    public NotificationDTO(NotificationType notificationType, Long id) {

        super(notificationType);

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
