package com.shopper.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AccountServiceException.class)
    public ResponseEntity<Object> exception(AccountServiceException exception) {
        return new ResponseEntity<>(exception.getErrorDetail(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorDetail> exception(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorDetail(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Object> exception(InvalidPasswordException exception) {
        return new ResponseEntity<>(exception.getErrorDetail(), HttpStatus.BAD_REQUEST);
    }

}
