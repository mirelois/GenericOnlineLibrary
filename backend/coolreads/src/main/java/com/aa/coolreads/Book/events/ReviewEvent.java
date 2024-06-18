package com.aa.coolreads.Book.events;

import org.springframework.context.ApplicationEvent;

public class ReviewEvent extends ApplicationEvent {

    private final ReviewMessage reviewMessage;

    public ReviewEvent(Object source, ReviewMessage reviewMessage){
        super(source);
        this.reviewMessage = reviewMessage;
    }

    public ReviewMessage getReviewMessage(){
        return reviewMessage;
    }
}
