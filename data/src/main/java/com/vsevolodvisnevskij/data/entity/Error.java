package com.vsevolodvisnevskij.data.entity;

public class Error extends Exception {
    private ErrorType friendyError;

    public Error(ErrorType friendyError) {
        this.friendyError = friendyError;
    }

    public ErrorType getFriendyError() {
        return friendyError;
    }
}
