package com.aa.coolreads.User.dto;

public class FriendDTO {

    private String username;

    private String url;

    private Integer friendCount;

    public FriendDTO(String username, String url, Integer friendCount) {
        this.username = username;
        this.url = url;
        this.friendCount = friendCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }
}
