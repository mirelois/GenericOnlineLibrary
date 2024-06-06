package com.aa.coolreads.User.dto;

import com.aa.coolreads.User.models.Privacy;

public class BookShelfCreationDTO {

    private String name;

    private String privacy;

    public BookShelfCreationDTO() {}

    public BookShelfCreationDTO(String name, String privacy) {
        this.name = name;
        this.privacy = privacy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
}
