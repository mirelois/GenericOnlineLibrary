package com.aa.coolreads.User.exception;

public class NoFriendRequestFromRequestedCustomerException extends Exception {
    public NoFriendRequestFromRequestedCustomerException(String username){
        super("THere are no friend request from customer " + username);
    }
}
