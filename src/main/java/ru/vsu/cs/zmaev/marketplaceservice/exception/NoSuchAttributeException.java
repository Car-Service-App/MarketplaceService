package ru.vsu.cs.zmaev.marketplaceservice.exception;

import org.springframework.http.HttpStatus;

public class NoSuchAttributeException extends EntityException {
    private static final String MESSAGE = "No such attribute with name: ";

    public NoSuchAttributeException(String errorMessage) {
        super(HttpStatus.NOT_FOUND, MESSAGE + errorMessage);
    }
}
