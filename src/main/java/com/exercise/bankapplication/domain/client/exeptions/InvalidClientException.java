package com.exercise.bankapplication.domain.client.exeptions;

public class InvalidClientException extends RuntimeException {
    public InvalidClientException(String message) {
        super(message);
    }
}
