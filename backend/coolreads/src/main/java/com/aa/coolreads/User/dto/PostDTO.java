package com.aa.coolreads.User.dto;

public class PostDTO extends PostCreationDTO{

    private Long id;

    public PostDTO(String title, String content, Long id) {
        super(title, content);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
